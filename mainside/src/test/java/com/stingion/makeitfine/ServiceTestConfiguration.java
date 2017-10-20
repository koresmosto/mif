package com.stingion.makeitfine;

import com.stingion.makeitfine.data.model.User;
import com.stingion.makeitfine.data.model.utils.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@TestConfiguration
@ImportResource("classpath:spring-test/context-test.xml")
public class ServiceTestConfiguration {

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public class EntityTestData<T> {
        private String findAll;
        private int id;
        private String findById;
        private String delete;

        private T savedEntity;
//
//        private T updateEntity;
//        private String udpate;
    }

    @Bean
    public EntityTestData<User> userTestData() {
        User user = new User();
        user.setSsoId("any_sso");
        user.setPassword("any_password");
        user.setEmail("any@email.any");
        user.setState(State.INACTIVE);

        return new EntityTestData<>(
                "[Worker(super=Contact(super=User(id=1, ssoId=bill, password=abc123, email=bill@xyz.com, state=Active, userProfiles=[UserProfile(id=1, type=USER)]), firstName=Bill, middleName=null, lastName=Watcher, birthDay=1981-08-08 00:00:00.0, phoneNumber=0735553432)), Worker(super=Contact(super=User(id=2, ssoId=danny, password=abc124, email=danny@xyz.com, state=Active, userProfiles=[UserProfile(id=1, type=USER)]), firstName=Danny, middleName=null, lastName=Theys, birthDay=1986-06-03 00:00:00.0, phoneNumber=0715558440)), Worker(super=Contact(super=User(id=3, ssoId=sam, password=abc125, email=samy@xyz.com, state=Inactive, userProfiles=[UserProfile(id=2, type=ADMIN)]), firstName=Sam, middleName=Middlware, lastName=Smith, birthDay=1982-08-30 00:00:00.0, phoneNumber=0725556495)), Customer(super=Contact(super=User(id=4, ssoId=nicole, password=abc126, email=nicloe@xyz.com, state=Locked, userProfiles=[UserProfile(id=3, type=DBA)]), firstName=Nicole, middleName=null, lastName=Warner, birthDay=1987-05-22 00:00:00.0, phoneNumber=null)), Worker(super=Contact(super=User(id=5, ssoId=kenny, password=abc127, email=kenny@xyz.com, state=Inactive, userProfiles=[UserProfile(id=2, type=ADMIN), UserProfile(id=3, type=DBA)]), firstName=Kenny, middleName=Kotton, lastName=Roger, birthDay=1980-08-29 00:00:00.0, phoneNumber=0725137211)), Customer(super=Contact(super=User(id=6, ssoId=sudo, password=nimda, email=sudo@xxx.xxx, state=Active, userProfiles=[UserProfile(id=1, type=USER), UserProfile(id=2, type=ADMIN), UserProfile(id=3, type=DBA)]), firstName=SUDO, middleName=null, lastName=SUDO, birthDay=1979-12-28 00:00:00.0, phoneNumber=null)), Customer(super=Contact(super=User(id=7, ssoId=tsuser, password=tspass1, email=tsuser@xxx.xxx, state=Active, userProfiles=[UserProfile(id=1, type=USER)]), firstName=Testside, middleName=null, lastName=Userino, birthDay=1978-04-03 00:00:00.0, phoneNumber=0715554032)), Worker(super=Contact(super=User(id=8, ssoId=admin, password=nimda, email=admin@xxx.xxx, state=Active, userProfiles=[UserProfile(id=2, type=ADMIN)]), firstName=Admin, middleName=null, lastName=Userino, birthDay=1980-08-29 00:00:00.0, phoneNumber=0725557763))]",
                3,
                "Worker(super=Contact(super=User(id=3, ssoId=sam, password=abc125, email=samy@xyz.com, state=Inactive, userProfiles=[UserProfile(id=2, type=ADMIN)]), firstName=Sam, middleName=Middlware, lastName=Smith, birthDay=1982-08-30 00:00:00.0, phoneNumber=0725556495))",
                "[Worker(super=Contact(super=User(id=1, ssoId=bill, password=abc123, email=bill@xyz.com, state=Active, userProfiles=[UserProfile(id=1, type=USER)]), firstName=Bill, middleName=null, lastName=Watcher, birthDay=1981-08-08 00:00:00.0, phoneNumber=0735553432)), Worker(super=Contact(super=User(id=2, ssoId=danny, password=abc124, email=danny@xyz.com, state=Active, userProfiles=[UserProfile(id=1, type=USER)]), firstName=Danny, middleName=null, lastName=Theys, birthDay=1986-06-03 00:00:00.0, phoneNumber=0715558440)), Customer(super=Contact(super=User(id=4, ssoId=nicole, password=abc126, email=nicloe@xyz.com, state=Locked, userProfiles=[UserProfile(id=3, type=DBA)]), firstName=Nicole, middleName=null, lastName=Warner, birthDay=1987-05-22 00:00:00.0, phoneNumber=null)), Worker(super=Contact(super=User(id=5, ssoId=kenny, password=abc127, email=kenny@xyz.com, state=Inactive, userProfiles=[UserProfile(id=2, type=ADMIN), UserProfile(id=3, type=DBA)]), firstName=Kenny, middleName=Kotton, lastName=Roger, birthDay=1980-08-29 00:00:00.0, phoneNumber=0725137211)), Customer(super=Contact(super=User(id=6, ssoId=sudo, password=nimda, email=sudo@xxx.xxx, state=Active, userProfiles=[UserProfile(id=1, type=USER), UserProfile(id=2, type=ADMIN), UserProfile(id=3, type=DBA)]), firstName=SUDO, middleName=null, lastName=SUDO, birthDay=1979-12-28 00:00:00.0, phoneNumber=null)), Customer(super=Contact(super=User(id=7, ssoId=tsuser, password=tspass1, email=tsuser@xxx.xxx, state=Active, userProfiles=[UserProfile(id=1, type=USER)]), firstName=Testside, middleName=null, lastName=Userino, birthDay=1978-04-03 00:00:00.0, phoneNumber=0715554032)), Worker(super=Contact(super=User(id=8, ssoId=admin, password=nimda, email=admin@xxx.xxx, state=Active, userProfiles=[UserProfile(id=2, type=ADMIN)]), firstName=Admin, middleName=null, lastName=Userino, birthDay=1980-08-29 00:00:00.0, phoneNumber=0725557763))]",
                user
        );
    }
}
