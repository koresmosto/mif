/*
 * Created under not commercial project
 */

package com.stingion.makeifine.data.model;

import com.stingion.makeifine.data.model.utils.UserProfileType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@Table(name = "User_Profile")
public class UserProfile {

    @TableGenerator(
            name = "UserProfile_gen",
            table = "SEQUENCES",
            pkColumnName = "SEQ_NAME",
            valueColumnName = "SEQ_NUMBER",
            pkColumnValue = "USER_PROFILE_SEQUENCE",
            allocationSize = 1)
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "UserProfile_gen")
    private int id;

    @Column
    private String type = UserProfileType.USER.getUserProfileType();
}
