package core;

import java.io.Serializable;

import lombok.Data;

@Data
// Data同下
//@Setter
//@Getter
//@ToString
//@EqualsAndHashCode
//@RequiredArgsConstructor(有加上final的constructor)
public class Core implements Serializable {
	private static final long serialVersionUID = 6873257004429928663L;
	private boolean successful;
	private String message;
}
