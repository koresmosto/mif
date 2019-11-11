/*
 * Created in scope of "Make it fine" project
 */

package com.stingion.makeitfine.data.model;

import com.stingion.makeitfine.data.model.utils.UserProfileType;
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
@EqualsAndHashCode(of = "type")
@Table(name = "USER_PROFILE")
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
