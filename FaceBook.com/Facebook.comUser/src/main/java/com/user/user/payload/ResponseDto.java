package com.user.user.payload;

import com.user.user.dto.FaceBookUserDto;
import com.user.user.dto.FbVisitorDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {

    private FaceBookUserDto faceBookUser;

    private FbVisitorDto fbVisitor;
}
