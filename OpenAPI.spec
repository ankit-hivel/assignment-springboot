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
                id:
                  type: integer
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
                role:
                  type: string
            example:
              id: 1011
              firstName: Emily
              lastName: Johnson
              maidenName: Smith
              age: 21
              gender: female
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
              role: admin
  /user:
    get:
      summary: getUserByIdOrEmail
      parameters:
        - name: email
          in: query
          required: false
          deprecated: false
          schema: {}
          example: emily.johnson@x.dummyjson.com
        - name: id
          in: query
          required: false
          deprecated: false
          schema: {}
          example: '22'
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
                address:
                  type: object
                  properties:
                    address:
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
                    lattitude:
                      type: number
                    longitude:
                      type: number
                    country:
                      type: string
                macAddress:
                  type: string
                  format: style
                university:
                  type: string
                bank:
                  type: object
                  properties:
                    cardExpire:
                      type: string
                    cardNumber:
                      type: string
                      format: utc-millisec
                    cardType:
                      type: string
                    currency:
                      type: string
                    iban:
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
                        address:
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
                        coordinates:
                          type: object
                          properties:
                            lat:
                              type: number
                            lng:
                              type: number
                        country:
                          type: string
                ein:
                  type: string
                ssn:
                  type: string
                userAgent:
                  type: string
                crypto:
                  type: object
                  properties:
                    coin:
                      type: string
                    wallet:
                      type: string
                      format: utc-millisec
                    network:
                      type: string
            example:
              id: 14
              firstName: Emily
              lastName: John
              maidenName: Smith
              gender: female
              email: emily.johnson@x.dummyjson.com
              phone: +81 965-431-3024
              username: emilys
              password: emilyspass
              birthDate: 1996-5-30
              image: https://dummyjson.com/icon/emilys/128
              bloodGroup: O-
              height: 193.24
              weight: 63.16
              eyeColor: Green
              haircolor: Brown
              hairtype: Curly
              ip: 42.48.100.32
              address:
                address: 626 Main Street
                city: Phoenix
                state: Mississippi
                stateCode: MS
                postalCode: '29112'
                lattitude: -77.16213
                longitude: -92.084824
                country: United States
              macAddress: 47:fa:41:18:ec:eb
              university: University of Wisconsin--Madison
              bank:
                cardExpire: 05/28
                cardNumber: '3693233511855044'
                cardType: Diners Club International
                currency: GBP
                iban: GB74MH2UZLR9TRPHYNU8F8
              company:
                department: Engineering
                name: Dooley, Kozey and Cronin
                title: Sales Manager
                address:
                  address: 263 Tenth Street
                  city: San Francisco
                  state: Wisconsin
                  stateCode: WI
                  postalCode: '37657'
                  coordinates:
                    lat: 71.814525
                    lng: -161.150263
                  country: United States
              ein: 977-175
              ssn: 900-590-289
              userAgent: >-
                Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7)
                AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.93
                Safari/537.36
              crypto:
                coin: Bitcoin
                wallet: '0xb9fc2fe63b2a6c003f1c324c3bfa53259162181a'
                network: Ethereum (ERC20)
    delete:
      summary: delete user data
      parameters:
        - name: id
          in: query
          schema:
            type: integer
          example: '14'
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
          schema:
            type: integer
          example: '1'
        - name: count
          in: query
          schema:
            type: integer
          example: '3'
        - name: sortby
          in: query
          schema:
            type: string
          example: username
        - name: ascending
          in: query
          schema:
            type: boolean
          example: 'false'
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
  /user/restore:
    put:
      summary: restore user data (soft restore)
      parameters:
        - name: id
          in: query
          schema:
            type: integer
          example: '13'
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
          example: '13'
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
              id: 62
              user_id: 13
              area: City
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
          example: '55'
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
                id:
                  type: integer
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
              id: 1
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
          example: '43'
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
              id: 45
              userid: 12
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
          schema:
            type: string
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
              properties:
                email:
                  type: string
                  format: email
                password:
                  type: string
            example:
              email: ankit@hivel.ai
              password: ankit
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
              example: Thu, 26 Feb 2026 12:08:51 GMT
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
