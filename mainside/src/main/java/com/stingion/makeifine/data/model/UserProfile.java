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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@Table(name = "User_Profile")
public class UserProfile {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_profile_autogen_id_gen")
    @SequenceGenerator(name = "user_profile_autogen_id_gen", sequenceName = "user_profile_id_seq", allocationSize = 1)
    private int id;

    @Column
    private String type = UserProfileType.USER.getUserProfileType();
}
