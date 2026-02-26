package com.ankit.assignmentspringboot.component;

import com.ankit.assignmentspringboot.utility.ApiResponse;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class RateLimitingFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(RateLimitingFilter.class);
    private final ConcurrentMap<String, Bucket> buckets = new ConcurrentHashMap<>();

    private Bucket getBucket(String clientId) {
        return buckets.computeIfAbsent(clientId, k ->
                Bucket.builder()
                        .addLimit(limit -> limit.capacity(10)
                                .refillIntervally(1, Duration.ofMinutes(1))
                        ).build()
        );
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String clientIp = httpRequest.getRemoteAddr();
        Bucket bucket = getBucket(clientIp);

        if (bucket.tryConsume(1)) {
            chain.doFilter(request, response);
        } else {
            log.info("limit exceeded for client: {}", clientIp);
            ObjectMapper objectMapper = new ObjectMapper();
            httpResponse.setStatus(429);
            httpResponse.setContentType("application/json;charset=UTF-8");
            httpResponse.getWriter().write(
                    objectMapper.writeValueAsString(
                            new ApiResponse<>(
                                    false,
                                    "api limit exceeded, please try again after sometime"
                            )
                    )
            );
            httpResponse.getWriter().flush();
        }
    }
}