openapi: 3.0.0
info:
  title: localhost
  version: 1.0.0
  description: ''
servers:
  - url: http://localhost:8080
paths:
  /user/save:
    post:
      summary: save user
      responses:
        '201':
          description: save user
          headers:
            X-Content-Type-Options:
              schema:
                type: string
              example: nosniff
            X-XSS-Protection:
              schema:
                type: integer
              example: '0'
            Cache-Control:
              schema:
                type: string
              example: no-cache, no-store, max-age=0, must-revalidate
            Pragma:
              schema:
                type: string
              example: no-cache
            Expires:
              schema:
                type: integer
              example: '0'
            X-Frame-Options:
              schema:
                type: string
              example: DENY
            Transfer-Encoding:
              schema:
                type: string
              example: chunked
            Date:
              schema:
                type: string
              example: Thu, 26 Feb 2026 11:58:27 GMT
            Keep-Alive:
              schema:
                type: string
              example: timeout=60
            Connection:
              schema:
                type: string
              example: keep-alive
      tags:
        - user api
      requestBody:
        content:
          application/json:
            schema:
              type: object
              properties:
                firstName:
                  type: string
                lastName:
                  type: string
                maidenName:
                  type: string
                age:
                  type: integer
                gender:
                  type: string
                email:
                  type: string
                  format: email
                phone:
                  type: string
                username:
                  type: string
                password:
                  type: string
                birthDate:
                  type: string
                image:
                  type: string
                  format: uri
                bloodGroup:
                  type: string
                height:
                  type: number
                weight:
                  type: number
                eyeColor:
                  type: string
                haircolor:
                  type: string
                hairtype:
                  type: string
                ip:
                  type: string
                  format: ip-address
                macAddress:
                  type: string
                  format: style
                university:
                  type: string
                ein:
                  type: string
                ssn:
                  type: string
                userAgent:
                  type: string
            example:
              firstName: Emily
              lastName: Johnson
              maidenName: Smith
              age: 21
              gender: male
              email: ankit@hivel.ai
              phone: +81 965-431-3024
              username: ankit
              password: ankit
              birthDate: 1996-5-30
              image: https://dummyjson.com/icon/emilys/128
              bloodGroup: O-
              height: 193.24
              weight: 63.16
              eyeColor: Green
              haircolor: Brown
              hairtype: Curly
              ip: 42.48.100.32
              macAddress: 47:fa:41:18:ec:eb
              university: University of Wisconsin--Madison
              ein: 977-175
              ssn: 900-590-289
              userAgent: >-
                Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7)
                AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.93
                Safari/537.36
  /user:
    get:
      summary: getUserByIdOrEmail
      parameters:
        - name: email
          in: query
          required: false
          deprecated: false
          schema: {}
          example: ankit@hivel.ai
        - name: id
          in: query
          required: false
          deprecated: false
          schema: {}
          example: '10'
      responses:
        '200':
          description: getUserByIdOrEmail
          headers:
            X-Content-Type-Options:
              schema:
                type: string
              example: nosniff
            X-XSS-Protection:
              schema:
                type: integer
              example: '0'
            Cache-Control:
              schema:
                type: string
              example: no-cache, no-store, max-age=0, must-revalidate
            Pragma:
              schema:
                type: string
              example: no-cache
            Expires:
              schema:
                type: integer
              example: '0'
            X-Frame-Options:
              schema:
                type: string
              example: DENY
            Transfer-Encoding:
              schema:
                type: string
              example: chunked
            Date:
              schema:
                type: string
              example: Thu, 26 Feb 2026 11:58:33 GMT
            Keep-Alive:
              schema:
                type: string
              example: timeout=60
            Connection:
              schema:
                type: string
              example: keep-alive
      tags:
        - user api
    put:
      summary: update user data
      responses:
        '200':
          description: update user data
          headers:
            X-Content-Type-Options:
              schema:
                type: string
              example: nosniff
            X-XSS-Protection:
              schema:
                type: integer
              example: '0'
            Cache-Control:
              schema:
                type: string
              example: no-cache, no-store, max-age=0, must-revalidate
            Pragma:
              schema:
                type: string
              example: no-cache
            Expires:
              schema:
                type: integer
              example: '0'
            X-Frame-Options:
              schema:
                type: string
              example: DENY
            Transfer-Encoding:
              schema:
                type: string
              example: chunked
            Date:
              schema:
                type: string
              example: Thu, 26 Feb 2026 11:59:14 GMT
            Keep-Alive:
              schema:
                type: string
              example: timeout=60
            Connection:
              schema:
                type: string
              example: keep-alive
      tags:
        - user api
      requestBody:
        content:
          application/json:
            schema:
              type: object
              properties:
                id:
                  type: integer
                firstName:
                  type: string
                lastName:
                  type: string
                maidenName:
                  type: string
                gender:
                  type: string
                email:
                  type: string
                  format: email
                phone:
                  type: string
                username:
                  type: string
                password:
                  type: string
                birthDate:
                  type: string
                image:
                  type: string
                  format: uri
                bloodGroup:
                  type: string
                height:
                  type: number
                weight:
                  type: number
                eyeColor:
                  type: string
                haircolor:
                  type: string
                hairtype:
                  type: string
                ip:
                  type: string
                  format: ip-address
                macAddress:
                  type: string
                  format: style
                university:
                  type: string
                ein:
                  type: string
                ssn:
                  type: string
                userAgent:
                  type: string
            example:
              id: 33
              firstName: Emily
              lastName: John
              maidenName: Smith
              gender: male
              email: ankit@hivel.ai
              phone: +81 965-431-3024
              username: ankit
              password: ankit
              birthDate: 1996-5-30
              image: https://dummyjson.com/icon/emilys/128
              bloodGroup: O-
              height: 193.24
              weight: 63.16
              eyeColor: Green
              haircolor: Brown
              hairtype: Curly
              ip: 42.48.100.32
              macAddress: 47:fa:41:18:ec:eb
              university: University of Wisconsin--Madison
              ein: 977-175
              ssn: 900-590-289
              userAgent: >-
                Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7)
                AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.93
                Safari/537.36
    delete:
      summary: delete user data
      parameters:
        - name: id
          in: query
          schema:
            type: integer
          example: '10'
      responses:
        '200':
          description: delete user data
          headers:
            X-Content-Type-Options:
              schema:
                type: string
              example: nosniff
            X-XSS-Protection:
              schema:
                type: integer
              example: '0'
            Cache-Control:
              schema:
                type: string
              example: no-cache, no-store, max-age=0, must-revalidate
            Pragma:
              schema:
                type: string
              example: no-cache
            Expires:
              schema:
                type: integer
              example: '0'
            X-Frame-Options:
              schema:
                type: string
              example: DENY
            Transfer-Encoding:
              schema:
                type: string
              example: chunked
            Date:
              schema:
                type: string
              example: Thu, 26 Feb 2026 11:59:21 GMT
            Keep-Alive:
              schema:
                type: string
              example: timeout=60
            Connection:
              schema:
                type: string
              example: keep-alive
      tags:
        - user api
  /user/all:
    get:
      summary: get all users
      parameters:
        - name: page
          in: query
          required: false
          deprecated: false
          schema: {}
          example: '0'
        - name: count
          in: query
          required: false
          deprecated: false
          schema: {}
          example: '300'
        - name: sortby
          in: query
          required: false
          deprecated: false
          schema: {}
          example: username
        - name: ascending
          in: query
          required: false
          deprecated: false
          schema: {}
          example: 'true'
      responses:
        '200':
          description: get all users
          headers:
            X-Content-Type-Options:
              schema:
                type: string
              example: nosniff
            X-XSS-Protection:
              schema:
                type: integer
              example: '0'
            Cache-Control:
              schema:
                type: string
              example: no-cache, no-store, max-age=0, must-revalidate
            Pragma:
              schema:
                type: string
              example: no-cache
            Expires:
              schema:
                type: integer
              example: '0'
            X-Frame-Options:
              schema:
                type: string
              example: DENY
            Transfer-Encoding:
              schema:
                type: string
              example: chunked
            Date:
              schema:
                type: string
              example: Thu, 26 Feb 2026 12:04:24 GMT
            Keep-Alive:
              schema:
                type: string
              example: timeout=60
            Connection:
              schema:
                type: string
              example: keep-alive
      tags:
        - user api
  /user/details:
    get:
      summary: get logged in user details
      responses:
        '200':
          description: get logged in user details
          headers:
            X-Content-Type-Options:
              schema:
                type: string
              example: nosniff
            X-XSS-Protection:
              schema:
                type: integer
              example: '0'
            Cache-Control:
              schema:
                type: string
              example: no-cache, no-store, max-age=0, must-revalidate
            Pragma:
              schema:
                type: string
              example: no-cache
            Expires:
              schema:
                type: integer
              example: '0'
            X-Frame-Options:
              schema:
                type: string
              example: DENY
            Transfer-Encoding:
              schema:
                type: string
              example: chunked
            Date:
              schema:
                type: string
              example: Fri, 27 Feb 2026 09:06:29 GMT
            Keep-Alive:
              schema:
                type: string
              example: timeout=60
            Connection:
              schema:
                type: string
              example: keep-alive
      tags:
        - user api
  /user/restore:
    put:
      summary: restore user data (soft restore)
      parameters:
        - name: id
          in: query
          schema:
            type: integer
          example: '11'
      responses:
        '200':
          description: restore user data (soft restore)
          headers:
            X-Content-Type-Options:
              schema:
                type: string
              example: nosniff
            X-XSS-Protection:
              schema:
                type: integer
              example: '0'
            Cache-Control:
              schema:
                type: string
              example: no-cache, no-store, max-age=0, must-revalidate
            Pragma:
              schema:
                type: string
              example: no-cache
            Expires:
              schema:
                type: integer
              example: '0'
            X-Frame-Options:
              schema:
                type: string
              example: DENY
            Transfer-Encoding:
              schema:
                type: string
              example: chunked
            Date:
              schema:
                type: string
              example: Thu, 26 Feb 2026 12:24:16 GMT
            Keep-Alive:
              schema:
                type: string
              example: timeout=60
            Connection:
              schema:
                type: string
              example: keep-alive
      tags:
        - user api
  /user/delete:
    delete:
      summary: delete user data (soft delete)
      parameters:
        - name: id
          in: query
          schema:
            type: integer
          example: '11'
      responses:
        '200':
          description: delete user data (soft delete)
          headers:
            X-Content-Type-Options:
              schema:
                type: string
              example: nosniff
            X-XSS-Protection:
              schema:
                type: integer
              example: '0'
            Cache-Control:
              schema:
                type: string
              example: no-cache, no-store, max-age=0, must-revalidate
            Pragma:
              schema:
                type: string
              example: no-cache
            Expires:
              schema:
                type: integer
              example: '0'
            X-Frame-Options:
              schema:
                type: string
              example: DENY
            Transfer-Encoding:
              schema:
                type: string
              example: chunked
            Date:
              schema:
                type: string
              example: Thu, 26 Feb 2026 11:59:33 GMT
            Keep-Alive:
              schema:
                type: string
              example: timeout=60
            Connection:
              schema:
                type: string
              example: keep-alive
      tags:
        - user api
  /address:
    post:
      summary: save user address
      responses:
        '201':
          description: save user address
          headers:
            X-Content-Type-Options:
              schema:
                type: string
              example: nosniff
            X-XSS-Protection:
              schema:
                type: integer
              example: '0'
            Cache-Control:
              schema:
                type: string
              example: no-cache, no-store, max-age=0, must-revalidate
            Pragma:
              schema:
                type: string
              example: no-cache
            Expires:
              schema:
                type: integer
              example: '0'
            X-Frame-Options:
              schema:
                type: string
              example: DENY
            Transfer-Encoding:
              schema:
                type: string
              example: chunked
            Date:
              schema:
                type: string
              example: Thu, 26 Feb 2026 12:02:53 GMT
            Keep-Alive:
              schema:
                type: string
              example: timeout=60
            Connection:
              schema:
                type: string
              example: keep-alive
      tags:
        - user address
      requestBody:
        content:
          application/json:
            schema:
              type: object
              properties:
                user_id:
                  type: integer
                area:
                  type: string
                city:
                  type: string
                state:
                  type: string
                stateCode:
                  type: string
                postalCode:
                  type: string
                  format: color
                lattitude:
                  type: number
                longitude:
                  type: number
                country:
                  type: string
            example:
              user_id: 1011
              area: Madhapur
              city: Hyderabad
              state: Telangana
              stateCode: TG
              postalCode: '500052'
              lattitude: -77.16213
              longitude: -92.084824
              country: United States
    get:
      summary: get user address by id
      parameters:
        - name: id
          in: query
          required: false
          deprecated: false
          schema: {}
          example: '26'
      responses:
        '200':
          description: get user address by id
          headers:
            X-Content-Type-Options:
              schema:
                type: string
              example: nosniff
            X-XSS-Protection:
              schema:
                type: integer
              example: '0'
            Cache-Control:
              schema:
                type: string
              example: no-cache, no-store, max-age=0, must-revalidate
            Pragma:
              schema:
                type: string
              example: no-cache
            Expires:
              schema:
                type: integer
              example: '0'
            X-Frame-Options:
              schema:
                type: string
              example: DENY
            Transfer-Encoding:
              schema:
                type: string
              example: chunked
            Date:
              schema:
                type: string
              example: Thu, 26 Feb 2026 12:04:03 GMT
            Keep-Alive:
              schema:
                type: string
              example: timeout=60
            Connection:
              schema:
                type: string
              example: keep-alive
      tags:
        - user address
    put:
      summary: update user address
      responses:
        '200':
          description: update user address
          headers:
            X-Content-Type-Options:
              schema:
                type: string
              example: nosniff
            X-XSS-Protection:
              schema:
                type: integer
              example: '0'
            Cache-Control:
              schema:
                type: string
              example: no-cache, no-store, max-age=0, must-revalidate
            Pragma:
              schema:
                type: string
              example: no-cache
            Expires:
              schema:
                type: integer
              example: '0'
            X-Frame-Options:
              schema:
                type: string
              example: DENY
            Transfer-Encoding:
              schema:
                type: string
              example: chunked
            Date:
              schema:
                type: string
              example: Thu, 26 Feb 2026 12:03:52 GMT
            Keep-Alive:
              schema:
                type: string
              example: timeout=60
            Connection:
              schema:
                type: string
              example: keep-alive
      tags:
        - user address
      requestBody:
        content:
          application/json:
            schema:
              type: object
              properties:
                id:
                  type: integer
                area:
                  type: string
                city:
                  type: string
                state:
                  type: string
                stateCode:
                  type: string
                postalCode:
                  type: string
                  format: color
                lattitude:
                  type: number
                longitude:
                  type: number
                country:
                  type: string
            example:
              id: 62
              area: City
              city: Hyderabad
              state: Telangana
              stateCode: TG
              postalCode: '500052'
              lattitude: -77.16213
              longitude: -92.084824
              country: United States
    delete:
      summary: delete user address by id
      parameters:
        - name: id
          in: query
          schema:
            type: integer
          example: '54'
      responses:
        '200':
          description: delete user address by id
          headers:
            X-Content-Type-Options:
              schema:
                type: string
              example: nosniff
            X-XSS-Protection:
              schema:
                type: integer
              example: '0'
            Cache-Control:
              schema:
                type: string
              example: no-cache, no-store, max-age=0, must-revalidate
            Pragma:
              schema:
                type: string
              example: no-cache
            Expires:
              schema:
                type: integer
              example: '0'
            X-Frame-Options:
              schema:
                type: string
              example: DENY
            Transfer-Encoding:
              schema:
                type: string
              example: chunked
            Date:
              schema:
                type: string
              example: Thu, 26 Feb 2026 12:04:12 GMT
            Keep-Alive:
              schema:
                type: string
              example: timeout=60
            Connection:
              schema:
                type: string
              example: keep-alive
      tags:
        - user address
  /company:
    post:
      summary: save company details
      responses:
        '201':
          description: save company details
          headers:
            X-Content-Type-Options:
              schema:
                type: string
              example: nosniff
            X-XSS-Protection:
              schema:
                type: integer
              example: '0'
            Cache-Control:
              schema:
                type: string
              example: no-cache, no-store, max-age=0, must-revalidate
            Pragma:
              schema:
                type: string
              example: no-cache
            Expires:
              schema:
                type: integer
              example: '0'
            X-Frame-Options:
              schema:
                type: string
              example: DENY
            Transfer-Encoding:
              schema:
                type: string
              example: chunked
            Date:
              schema:
                type: string
              example: Thu, 26 Feb 2026 12:04:46 GMT
            Keep-Alive:
              schema:
                type: string
              example: timeout=60
            Connection:
              schema:
                type: string
              example: keep-alive
      tags:
        - user company
      requestBody:
        content:
          application/json:
            schema:
              type: object
              properties:
                userid:
                  type: integer
                department:
                  type: string
                name:
                  type: string
                title:
                  type: string
                address:
                  type: object
                  properties:
                    area:
                      type: string
                    city:
                      type: string
                    state:
                      type: string
                    stateCode:
                      type: string
                    postalCode:
                      type: string
                      format: utc-millisec
                    lat:
                      type: number
                    lng:
                      type: number
                    country:
                      type: string
            example:
              userid: 1011
              department: Engineering
              name: Dooley, Kozey and Cronin
              title: Sales Manager
              address:
                area: 263 Tenth Street
                city: San Francisco
                state: Wisconsin
                stateCode: WI
                postalCode: '37657'
                lat: 71.814525
                lng: -161.150263
                country: United States
    get:
      summary: get company details by id
      parameters:
        - name: id
          in: query
          schema:
            type: integer
          example: '31'
      responses:
        '200':
          description: get company details by id
          headers:
            X-Content-Type-Options:
              schema:
                type: string
              example: nosniff
            X-XSS-Protection:
              schema:
                type: integer
              example: '0'
            Cache-Control:
              schema:
                type: string
              example: no-cache, no-store, max-age=0, must-revalidate
            Pragma:
              schema:
                type: string
              example: no-cache
            Expires:
              schema:
                type: integer
              example: '0'
            X-Frame-Options:
              schema:
                type: string
              example: DENY
            Transfer-Encoding:
              schema:
                type: string
              example: chunked
            Date:
              schema:
                type: string
              example: Thu, 26 Feb 2026 12:05:16 GMT
            Keep-Alive:
              schema:
                type: string
              example: timeout=60
            Connection:
              schema:
                type: string
              example: keep-alive
      tags:
        - user company
    put:
      summary: update company details
      responses:
        '200':
          description: update company details
          headers:
            X-Content-Type-Options:
              schema:
                type: string
              example: nosniff
            X-XSS-Protection:
              schema:
                type: integer
              example: '0'
            Cache-Control:
              schema:
                type: string
              example: no-cache, no-store, max-age=0, must-revalidate
            Pragma:
              schema:
                type: string
              example: no-cache
            Expires:
              schema:
                type: integer
              example: '0'
            X-Frame-Options:
              schema:
                type: string
              example: DENY
            Transfer-Encoding:
              schema:
                type: string
              example: chunked
            Date:
              schema:
                type: string
              example: Thu, 26 Feb 2026 12:07:37 GMT
            Keep-Alive:
              schema:
                type: string
              example: timeout=60
            Connection:
              schema:
                type: string
              example: keep-alive
      tags:
        - user company
      requestBody:
        content:
          application/json:
            schema:
              type: object
              properties:
                id:
                  type: integer
                department:
                  type: string
                name:
                  type: string
                title:
                  type: string
                address:
                  type: object
                  properties:
                    area:
                      type: string
                    city:
                      type: string
                    state:
                      type: string
                    stateCode:
                      type: string
                    postalCode:
                      type: string
                      format: utc-millisec
                    lat:
                      type: number
                    lng:
                      type: number
                    country:
                      type: string
            example:
              id: 45
              department: Sales
              name: Dooley, Kozey and Cronin
              title: Sales Manager
              address:
                area: 26 Tenth Street
                city: San Francisco
                state: Wisconsin
                stateCode: WI
                postalCode: '37657'
                lat: 71.814525
                lng: -161.150263
                country: India
    delete:
      summary: delete company details
      parameters:
        - name: id
          in: query
          required: false
          deprecated: false
          schema: {}
          example: '43'
      responses:
        '200':
          description: delete company details
          headers:
            X-Content-Type-Options:
              schema:
                type: string
              example: nosniff
            X-XSS-Protection:
              schema:
                type: integer
              example: '0'
            Cache-Control:
              schema:
                type: string
              example: no-cache, no-store, max-age=0, must-revalidate
            Pragma:
              schema:
                type: string
              example: no-cache
            Expires:
              schema:
                type: integer
              example: '0'
            X-Frame-Options:
              schema:
                type: string
              example: DENY
            Transfer-Encoding:
              schema:
                type: string
              example: chunked
            Date:
              schema:
                type: string
              example: Thu, 26 Feb 2026 12:07:51 GMT
            Keep-Alive:
              schema:
                type: string
              example: timeout=60
            Connection:
              schema:
                type: string
              example: keep-alive
      tags:
        - user company
  /auth/login:
    post:
      summary: login user
      parameters:
        - name: Authorization
          in: header
          required: false
          deprecated: false
          schema: {}
          example: >-
            Bearer
            eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMzEyIiwiaWF0IjoxNzcyMDE3ODc2LCJleHAiOjE3NzIwMTc4NzZ9.m3KYnVP8703KrOx-xDsU-27q4qBRPRimTnZ8AX0ycd4
      responses:
        '200':
          description: login user
          headers:
            Set-Cookie:
              schema:
                type: string
              example: >-
                token=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDExIiwiaWF0IjoxNzcyMTA3NzEwLCJleHAiOjE3NzIxMTEzMTB9.pc0LhbvMkuMRtiuR_HL3A35g_olp2kH1MbwzH0NOqoY;
                Path=/; Max-Age=3600; Expires=Thu, 26 Feb 2026 13:08:30 GMT;
                Secure; HttpOnly; SameSite=Strict
            X-Content-Type-Options:
              schema:
                type: string
              example: nosniff
            X-XSS-Protection:
              schema:
                type: integer
              example: '0'
            Cache-Control:
              schema:
                type: string
              example: no-cache, no-store, max-age=0, must-revalidate
            Pragma:
              schema:
                type: string
              example: no-cache
            Expires:
              schema:
                type: integer
              example: '0'
            X-Frame-Options:
              schema:
                type: string
              example: DENY
            Transfer-Encoding:
              schema:
                type: string
              example: chunked
            Date:
              schema:
                type: string
              example: Thu, 26 Feb 2026 12:08:30 GMT
            Keep-Alive:
              schema:
                type: string
              example: timeout=60
            Connection:
              schema:
                type: string
              example: keep-alive
      tags:
        - authenticate
      requestBody:
        content:
          application/json:
            schema:
              type: object
            example: |-
              {
                  "email": "ankit@hivel.ai",
                  "password": "ankit"
              }
              // {
              //     "email": "emily.johnson@x.dummyjson.com",
              //     "password": "emilyspass"
              // }
  /health:
    get:
      summary: health check
      responses:
        '200':
          description: health check
          headers:
            X-Content-Type-Options:
              schema:
                type: string
              example: nosniff
            X-XSS-Protection:
              schema:
                type: integer
              example: '0'
            Cache-Control:
              schema:
                type: string
              example: no-cache, no-store, max-age=0, must-revalidate
            Pragma:
              schema:
                type: string
              example: no-cache
            Expires:
              schema:
                type: integer
              example: '0'
            X-Frame-Options:
              schema:
                type: string
              example: DENY
            Transfer-Encoding:
              schema:
                type: string
              example: chunked
            Date:
              schema:
                type: string
              example: Fri, 27 Feb 2026 07:30:49 GMT
            Keep-Alive:
              schema:
                type: string
              example: timeout=60
            Connection:
              schema:
                type: string
              example: keep-alive
      tags:
        - metrics
  /csv:
    get:
      summary: export to csv
      responses:
        '200':
          description: export to csv
          headers:
            X-Content-Type-Options:
              schema:
                type: string
              example: nosniff
            X-XSS-Protection:
              schema:
                type: integer
              example: '0'
            Cache-Control:
              schema:
                type: string
              example: no-cache, no-store, max-age=0, must-revalidate
            Pragma:
              schema:
                type: string
              example: no-cache
            Expires:
              schema:
                type: integer
              example: '0'
            X-Frame-Options:
              schema:
                type: string
              example: DENY
            Transfer-Encoding:
              schema:
                type: string
              example: chunked
            Date:
              schema:
                type: string
              example: Thu, 26 Feb 2026 12:23:22 GMT
            Keep-Alive:
              schema:
                type: string
              example: timeout=60
            Connection:
              schema:
                type: string
              example: keep-alive
      tags:
        - metrics
  /metrics:
    get:
      summary: get metrics
      responses:
        '200':
          description: get metrics
          headers:
            X-Content-Type-Options:
              schema:
                type: string
              example: nosniff
            X-XSS-Protection:
              schema:
                type: integer
              example: '0'
            Cache-Control:
              schema:
                type: string
              example: no-cache, no-store, max-age=0, must-revalidate
            Pragma:
              schema:
                type: string
              example: no-cache
            Expires:
              schema:
                type: integer
              example: '0'
            X-Frame-Options:
              schema:
                type: string
              example: DENY
            Transfer-Encoding:
              schema:
                type: string
              example: chunked
            Date:
              schema:
                type: string
              example: Thu, 26 Feb 2026 12:23:53 GMT
            Keep-Alive:
              schema:
                type: string
              example: timeout=60
            Connection:
              schema:
                type: string
              example: keep-alive
      tags:
        - metrics
  /files/101260088436081000.csv:
    get:
      summary: download csv file
      responses:
        '200':
          description: download csv file
          headers:
            Last-Modified:
              schema:
                type: string
              example: Fri, 27 Feb 2026 06:39:26 GMT
            Accept-Ranges:
              schema:
                type: string
              example: bytes
            X-Content-Type-Options:
              schema:
                type: string
              example: nosniff
            X-XSS-Protection:
              schema:
                type: integer
              example: '0'
            Cache-Control:
              schema:
                type: string
              example: no-cache, no-store, max-age=0, must-revalidate
            Pragma:
              schema:
                type: string
              example: no-cache
            Expires:
              schema:
                type: integer
              example: '0'
            X-Frame-Options:
              schema:
                type: string
              example: DENY
            Content-Length:
              schema:
                type: integer
              example: '21222'
            Date:
              schema:
                type: string
              example: Fri, 27 Feb 2026 06:40:57 GMT
            Keep-Alive:
              schema:
                type: string
              example: timeout=60
            Connection:
              schema:
                type: string
              example: keep-alive
      tags:
        - metrics
  /bulk/insert:
    post:
      summary: bulk insertion
      responses:
        '201':
          description: bulk insertion
          headers:
            X-Content-Type-Options:
              schema:
                type: string
              example: nosniff
            X-XSS-Protection:
              schema:
                type: integer
              example: '0'
            Cache-Control:
              schema:
                type: string
              example: no-cache, no-store, max-age=0, must-revalidate
            Pragma:
              schema:
                type: string
              example: no-cache
            Expires:
              schema:
                type: integer
              example: '0'
            X-Frame-Options:
              schema:
                type: string
              example: DENY
            Transfer-Encoding:
              schema:
                type: string
              example: chunked
            Date:
              schema:
                type: string
              example: Mon, 02 Mar 2026 07:03:04 GMT
            Keep-Alive:
              schema:
                type: string
              example: timeout=60
            Connection:
              schema:
                type: string
              example: keep-alive
      tags:
        - bulk operations
      requestBody:
        content:
          application/json:
            schema:
              type: array
              items:
                type: object
                properties:
                  user:
                    type: object
                    properties:
                      firstName:
                        type: string
                      lastName:
                        type: string
                      maidenName:
                        type: string
                      age:
                        type: integer
                      gender:
                        type: string
                      email:
                        type: string
                        format: email
                      phone:
                        type: string
                      username:
                        type: string
                      password:
                        type: string
                      birthDate:
                        type: string
                      image:
                        type: string
                        format: uri
                      bloodGroup:
                        type: string
                      height:
                        type: number
                      weight:
                        type: number
                      eyeColor:
                        type: string
                      haircolor:
                        type: string
                      hairtype:
                        type: string
                      ip:
                        type: string
                        format: ip-address
                      macAddress:
                        type: string
                        format: style
                      university:
                        type: string
                      ein:
                        type: string
                      ssn:
                        type: string
                      userAgent:
                        type: string
                  address:
                    type: object
                    properties:
                      area:
                        type: string
                      city:
                        type: string
                      state:
                        type: string
                      stateCode:
                        type: string
                      postalCode:
                        type: string
                        format: color
                      lattitude:
                        type: number
                      longitude:
                        type: number
                      country:
                        type: string
                  company:
                    type: object
                    properties:
                      department:
                        type: string
                      name:
                        type: string
                      title:
                        type: string
                      address:
                        type: object
                        properties:
                          area:
                            type: string
                          city:
                            type: string
                          state:
                            type: string
                          stateCode:
                            type: string
                          postalCode:
                            type: string
                            format: utc-millisec
                          lat:
                            type: number
                          lng:
                            type: number
                          country:
                            type: string
            example:
              - user:
                  firstName: Emily
                  lastName: Johnson
                  maidenName: Smith
                  age: 21
                  gender: male
                  email: ankit@hivel.aihh
                  phone: +81 965-431-3024
                  username: ankithh
                  password: ankit
                  birthDate: 1996-5-30
                  image: https://dummyjson.com/icon/emilys/128
                  bloodGroup: O-
                  height: 193.24
                  weight: 63.16
                  eyeColor: Green
                  haircolor: Brown
                  hairtype: Curly
                  ip: 42.48.100.32
                  macAddress: 47:fa:41:18:ec:eb
                  university: University of Wisconsin--Madison
                  ein: 977-175
                  ssn: 900-590-289
                  userAgent: >-
                    Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7)
                    AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.93
                    Safari/537.36
                address:
                  area: Madhapur
                  city: Hyderabad
                  state: Telangana
                  stateCode: TG
                  postalCode: '500052'
                  lattitude: -77.16213
                  longitude: -92.084824
                  country: United States
                company:
                  department: Engineering
                  name: Dooley, Kozey and Cronin
                  title: Sales Manager
                  address:
                    area: 263 Tenth Street
                    city: San Francisco
                    state: Wisconsin
                    stateCode: WI
                    postalCode: '37657'
                    lat: 71.814525
                    lng: -161.150263
                    country: United States
              - user:
                  firstName: Emily
                  lastName: Johnson
                  maidenName: Smith
                  age: 21
                  gender: male
                  email: ankit@hivel.aihah
                  phone: +81 965-431-3024
                  username: ankithsh
                  password: ankit
                  birthDate: 1996-5-30
                  image: https://dummyjson.com/icon/emilys/128
                  bloodGroup: O-
                  height: 193.24
                  weight: 63.16
                  eyeColor: Green
                  haircolor: Brown
                  hairtype: Curly
                  ip: 42.48.100.32
                  macAddress: 47:fa:41:18:ec:eb
                  university: University of Wisconsin--Madison
                  ein: 977-175
                  ssn: 900-590-289
                  userAgent: >-
                    Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7)
                    AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.93
                    Safari/537.36
                address:
                  area: Madhapur
                  city: Hyderabad
                  state: Telangana
                  stateCode: TG
                  postalCode: '500052'
                  lattitude: -77.16213
                  longitude: -92.084824
                  country: United States
                company:
                  department: Engineering
                  name: Dooley, Kozey and Cronin
                  title: Sales Manager
                  address:
                    area: 263 Tenth Street
                    city: San Francisco
                    state: Wisconsin
                    stateCode: WI
                    postalCode: '37657'
                    lat: 71.814525
                    lng: -161.150263
                    country: United States
  /bulk/delete:
    delete:
      summary: bulk deletion
      responses:
        '201':
          description: bulk deletion
          headers:
            X-Content-Type-Options:
              schema:
                type: string
              example: nosniff
            X-XSS-Protection:
              schema:
                type: integer
              example: '0'
            Cache-Control:
              schema:
                type: string
              example: no-cache, no-store, max-age=0, must-revalidate
            Pragma:
              schema:
                type: string
              example: no-cache
            Expires:
              schema:
                type: integer
              example: '0'
            X-Frame-Options:
              schema:
                type: string
              example: DENY
            Transfer-Encoding:
              schema:
                type: string
              example: chunked
            Date:
              schema:
                type: string
              example: Mon, 02 Mar 2026 07:03:01 GMT
            Keep-Alive:
              schema:
                type: string
              example: timeout=60
            Connection:
              schema:
                type: string
              example: keep-alive
      tags:
        - bulk operations
      requestBody:
        content:
          application/json:
            schema:
              type: object
              properties:
                ids:
                  type: array
                  items:
                    type: integer
            example:
              ids:
                - 1
                - 2
                - 3
                - 4
