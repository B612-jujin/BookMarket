package kr.ac.kopo.cjj.bookmarket.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class MemberFormDto {
    @NotBlank(message = "ID는 필수 입력 값입니다.")
    private String memberId;
    @NotBlank(message = "패스워드는 필수 입력 값입니다.")
    @Length(min=8, max=16, message = "패스워드는 최소 6자리 이상 최대 16자리 이하로 지정해주셔야 합니다.")
    private String password;
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;
    @NotBlank(message = "휴대폰 번호는 필수 입력 값입니다.")
    private String phone;
    @NotBlank(message = "이메일 주소는 필수 입력 값입니다.")
    @Email(message = "이메일 형식이 잘못되었습니다.")
    private String email;
    @NotBlank(message = "주소는 필수 입력 값입니다.")
    private String address;
}