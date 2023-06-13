package ad.course.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    private int courseAD;
    private int courseID;
    private String courseName;
    private Date startDate;
    private Date endDate;
    private String courseIntroduce;
    private Integer courseMax;
    private Integer courseMin;
    private Integer coursePerson;
    private Integer coursePrice;
    private byte[] coursePhoto;
    private Integer level;   
    private Integer coachID;
    private Integer skill;
    private Integer pointID;
    private Date courseDate;
    private Integer courseStatus;

}
