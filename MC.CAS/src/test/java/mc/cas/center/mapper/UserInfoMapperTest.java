package mc.cas.center.mapper;

import mc.cas.center.TestConfig;
import mc.cas.center.pojo.UserInfoPojo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
public class UserinfoMapperTest {
    @Autowired
    UserinfoMapper userInfoMapper;

    @Test
    public void getUserInfoByIdTest(){
        UserInfoPojo userInfoById = userInfoMapper.getUserInfoById(1);
        System.out.println(userInfoById.toString());
    }

    @Test
    public void get(){
        List<UserInfoPojo> userInfoList = userInfoMapper.getUserInfoList(0, 1);
        for (UserInfoPojo userInfoPojo : userInfoList) {
            System.out.println(userInfoPojo.toString());
        }
    }
}
