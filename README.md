to validate spring security using database -> 
1. build user entity class
2. 2.build userrepo findByusername
3. 3.Implement userDetailsService in service layer
4. 4.Make SecurityConfig class in com.test.config
5. 5.draw controller to register and login

               ┌────────────┐
            │  User hits  │
            │ /login API  │
            └──────┬─────┘
                   │ username + password
                   ▼
       ┌──────────────────────────┐
       │ AuthenticationManager     │
       └───────────┬──────────────┘
                   │ calls
                   ▼
     ┌─────────────────────────────┐
     │ MyUserDetailsService        │
     └────────────┬────────────────┘
                  │ calls
                  ▼
        ┌───────────────────────┐
        │ UserRepository (DB)   │
        └───────────┬───────────┘
                    │ returns User
                    ▼
       ┌──────────────────────────┐
       │ Spring Security matches  │
       │ raw pwd ↔ encoded pwd    │
       └───────────┬──────────────┘
                   │
                   ▼
        ┌────────────────────────┐
        │ Authentication SUCCESS │
        └────────────────────────┘
