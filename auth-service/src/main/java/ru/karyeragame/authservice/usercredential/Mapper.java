package ru.karyeragame.authservice.usercredential;

public class Mapper {
    public static UserCredential toUserCredential(UserCredentialDto userCredentialDto) {
        UserCredential userCredential = new UserCredential();
        userCredential.setEmail(userCredentialDto.getEmail());
        userCredential.setPassword(userCredentialDto.getPassword());
        return userCredential;
    }

    public static ResponseUserCredDto toDto(UserCredential userCredential) {
        return new ResponseUserCredDto(
                userCredential.getId(),
                userCredential.getEmail()
        );
    }
}
