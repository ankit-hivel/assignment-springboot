## AssignmentSpringBoot

This is a Spring Boot 4 / Java 17 application that manages users, their addresses and companies, with JWT‑based authentication, Redis caching, rate limiting and basic health/metrics endpoints.

### High‑level architecture

- **Entry point (`AssignmentSpringBootApplication`)**: Bootstraps the Spring Boot application and enables async processing with `@EnableAsync`.
- **Layers**:
  - **Controller layer (`controller` package)**: Exposes REST APIs.
  - **Service layer (`service` package)**: Contains business logic, external API calls and caching.
  - **Persistence layer (`model` + `repository` packages)**: JPA entities and repositories for PostgreSQL.
  - **Infrastructure / cross‑cutting (`component`, `config`, `utility`, `utility.security`)**: Security, filters, Redis, helpers and DTOs.

### Main components and their responsibilities

- **Controllers (`com.ankit.assignmentspringboot.controller`)**
  - **`UserController`**: CRUD operations for users (`/user`).
    - `POST /user/save`: Create user (password is hashed in the service).
    - `GET /user/details`: Get profile of the currently authenticated user.
    - `GET /user?id=...` and `GET /user?email=...`: Admin/moderator‑only lookups.
    - `GET /user/all`: Paged and sorted listing (admin/moderator).
    - `PUT /user`: Update user profile with authorization checks.
    - `DELETE /user`: Hard delete (with cache invalidation).
    - `DELETE /user/delete` and `PUT /user/restore`: Soft delete / restore by id.
  - **`AuthController`**: Login endpoint (`/auth/login`).
    - Validates credentials via `AuthService`, generates a JWT, and sets it as an HTTP‑only, secure cookie named `token`.
  - **`UserAddressController` / `CompanyController`**: Manage addresses and company information linked to users (create/update/fetch using the corresponding services and DTOs).
  - **`MetricsController`**:
    - `GET /health`: Checks DB and Redis connectivity in parallel and returns a `HealthResponseDto`.
    - `GET /csv`: Exports all user data (with related address and company) to `./static/data.csv` using OpenCSV.
    - `GET /metrics`: Returns cache hit/miss counts stored in Redis as `MetricsResponseDto`.

- **Services (`com.ankit.assignmentspringboot.service`)**
  - **`UserService`**:
    - Creates users from `SaveUserRequestDto` and hashes passwords with BCrypt.
    - Reads a user by id or email, using Redis for caching (JSON is stored with a TTL and converted back using Jackson).
    - Returns paginated, sorted lists of users.
    - Performs updates with detailed field‑by‑field merging and permission checks (self or admin/moderator).
    - Handles hard delete, soft delete, and restore, and invalidates the relevant cache entries.
  - **`UserAddressService`** / **`CompanyService`**:
    - Map incoming DTOs to JPA models (`UserAddressModel`, `CompanyModel`, `CompanyAddressModel`), persist them and handle basic update/read logic.
  - **`AuthService`**:
    - Looks up a user by email, verifies the BCrypt password hash, and issues a JWT token via `JwtService`.
  - **`RestApiService`**:
    - Async service that fetches users from the external API `https://dummyjson.com/users`.
    - Maps the external payload to internal `SaveUserRequestDto`, `SaveUserAddressRequestDto`, `CompanyAddressDto` and `SaveCompanyRequestDto`.
    - Persists user, address and company records via `UserService`, `UserAddressService` and `CompanyService`.
  - **`RedisService`**:
    - Wraps `StringRedisTemplate` to provide simple methods for `ping`, `save`, `saveWithTTL`, `get`, and `del`.
    - Each cache `get` increments either a global cache miss or cache hit counter in Redis (used by `/metrics`).

- **Persistence (`model` and `repository` packages)**
  - **Models**:
    - `UserModel`, `UserAddressModel`, `CompanyModel`, `CompanyAddressModel`: JPA entities mapped to PostgreSQL tables. `UserModel` holds personal data, role (`UserRole`) and relationships to address and company (with soft delete support via an `isDeleted` flag).
  - **Repositories**:
    - `UserRepository`, `UserAddressRepository`, `CompanyRepository`, `CompanyAddressRepository`: Spring Data JPA repositories with convenience finders (e.g. `findByIdAndIsDeleted`, `findByEmailIgnoreCaseAndIsDeleted`, paginated `findAllByIsDeleted(false, Pageable)`).

- **Security and filters (`config`, `component`, `utility.security`)**
  - **`SecurityConfig`**:
    - Configures the Spring Security filter chain.
    - Disables CSRF for simplicity.
    - Allows unauthenticated access to `/auth/login`, `/user/save`, `/health`, `/csv`, `/metrics`, and `/files/*`.
    - Requires authentication for all other endpoints and registers `JwtAuthenticationFilter` before `UsernamePasswordAuthenticationFilter`.
  - **`JwtAuthenticationFilter`**:
    - Once‑per‑request filter that:
      - Skips a fixed list of public paths.
      - Extracts the JWT from the `token` cookie.
      - Validates it via `JwtService` and, on success, sets a `UsernamePasswordAuthenticationToken` in the `SecurityContextHolder` with the user id as the principal.
      - On failure, returns `401` with a JSON `ApiResponse` body.
  - **`JwtService`**:
    - Uses `io.jsonwebtoken` (JJWT) with a symmetric secret key (loaded from `jwt.secret` and `jwt.expiration` in configuration).
    - Provides `generateToken(userId)`, `getUserIdFromToken(token)` and `validateJwtToken(token)` with detailed logging of invalid/expired tokens.
  - **`GetAuthUserId`** / **`GetAuthUserRole`**:
    - Helper classes that read the current authenticated user id from the security context and resolve the user’s `UserRole` through `UserRepository`.
    - Widely used in controllers and services to enforce authorization (self vs admin/moderator).
  - **`RateLimitingFilter`**:
    - Servlet filter using Bucket4j to rate limit by client IP.
    - Allows 10 requests per minute per IP; when the bucket is empty it returns HTTP 429 with a JSON `ApiResponse` and a `Retry-After` header in seconds.
  - **`StartupRunner`**:
    - `CommandLineRunner` that executes on application startup.
    - Pings Redis using `RedisService.ping()` and fails fast (throws `RedisConnectionFailureException`) if the connection is not healthy.

- **DTOs and utility classes (`requestDto`, `responseDto`, `utility`)**
  - **Request DTOs**:
    - `SaveUserRequestDto`, `UpdateUserAddressRequestDto`, `SaveUserAddressRequestDto`, `SaveCompanyRequestDto`, `UpdateCompanyRequestDto`, `CompanyAddressDto`, `LoginRequestDto`:
      - Represent input payloads for creating/updating users, addresses, companies and for login.
  - **Response DTOs**:
    - `GetUserResponseDto`, `GetUserAddressResponseDto`, `GetCompanyResponseDto`, `HealthResponseDto`, `MetricsResponseDto`, `UserExportDataToCsvDto`, `AuthResponseDto`, `GetAllUsersDummyApiResponse`, `responseDto.User`:
      - Shape the data returned by APIs (and the CSV export) without leaking internal JPA entities.
  - **`ApiResponse<T>`**:
    - Generic response wrapper that all controllers use, with `status`, `message` and optional `data` fields to keep responses consistent.
  - **Other helpers**:
    - `CONSTANTS`: Central place for application constants (e.g. Redis key names, cache metrics keys).
    - `UserRole`: Enum describing application roles (e.g. `ADMIN`, `MODERATOR`, `USER`).
    - `DateSerializer`, `FutureResults`: Small utilities for JSON date formatting and safe retrieval of values from `Future` tasks with logging and timeouts.

### How authentication and authorization work (brief)

- **Login**:
  - Client calls `POST /auth/login` with email and password.
  - `AuthService` validates credentials and generates a JWT containing the user id.
  - `AuthController` sends this token back as an HTTP‑only, secure cookie named `token`.
- **Authenticated requests**:
  - `JwtAuthenticationFilter` runs on every non‑public path.
  - The filter reads the `token` cookie, validates it and sets the authenticated user id in the Spring Security context.
  - Helpers (`GetAuthUserId`, `GetAuthUserRole`) are then used by controllers/services to decide if the current user can perform an operation (e.g. only admins/moderators can list all users).

### Running the application (summary)

- **Prerequisites**:
  - Java 17+
  - Maven
  - PostgreSQL instance (configured in `application.properties`/`application.yml`).
  - Redis instance (for caching and metrics).
  - `jwt.secret` and `jwt.expiration` configured in your application configuration.
- **Build & run**:
  - `mvn clean install`
  - `mvn spring-boot:run`

Once running, you can:
- Register users via `/user/save` and log in via `/auth/login`.
- Call protected endpoints with the JWT cookie attached.
- Check `/health`, `/metrics` and `/csv` for health, cache metrics and CSV export.

