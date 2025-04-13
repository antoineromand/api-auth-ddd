package com.dxs.auth.infrastructure.adapter;

import com.dxs.auth.core.entity.IUserFactory;
import com.dxs.auth.core.external.IPasswordEncrypt;
import com.dxs.auth.core.external.TokenManager;
import com.dxs.auth.core.external.repository.AbstractUserRepository;
import com.dxs.auth.core.response.Response;
import com.dxs.auth.core.usecase.LoginUseCase;
import com.dxs.auth.core.usecase.RegisterUseCase;
import com.dxs.auth.core.usecase.VerifyTokenUseCase;
import com.dxs.auth.infrastructure.model.UserModel;
import com.dxs.auth.web.dto.auth.register.RegisterInputDTO;
import org.springframework.stereotype.Component;

@Component
public class AuthCoreAdapter {

    private final RegisterUseCase<UserModel, RegisterInputDTO> registerUseCase;
    private final LoginUseCase<UserModel> loginUseCase;
    private final VerifyTokenUseCase verifyTokenUseCase;
    public AuthCoreAdapter(
            AbstractUserRepository<UserModel> userRepository,
            IPasswordEncrypt passwordEncrypt,
            IUserFactory<UserModel, RegisterInputDTO> userFactory,
            TokenManager tokenManager
    ) {
        this.registerUseCase = new RegisterUseCase<>(userRepository, passwordEncrypt, userFactory);
        this.loginUseCase = new LoginUseCase<>(userRepository, passwordEncrypt, tokenManager);
        this.verifyTokenUseCase = new VerifyTokenUseCase(tokenManager);
    }

    public Response<UserModel> register(RegisterInputDTO input) {
        return registerUseCase.register(input);
    }

    public Response<String> login(String email, String password) {
        return loginUseCase.login(email, password);
    }

    public boolean verifyToken(String jwt) { return this.verifyTokenUseCase.execute(jwt);}
}

