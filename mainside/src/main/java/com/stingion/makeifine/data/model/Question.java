/**
 * Created under not commercial project
 */
package com.stingion.makeifine.data.model;

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
@EqualsAndHashCode
@Table(name = "Question")
public class Question {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_autogen_id_gen")
    @SequenceGenerator(name = "question_autogen_id_gen", sequenceName = "question_id_seq", allocationSize = 1)
    private Integer id;
    @SuppressWarnings({"squid:S1700"})
    private String question;
    private String variant1; //NOSONAR
    private String variant2; //NOSONAR
    private String variant3; //NOSONAR
    private String variant4; //NOSONAR
    private Integer answer; //NOSONAR
}
