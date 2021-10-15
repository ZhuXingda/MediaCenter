package mc.cas.center.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: ZhuXingda
 * @Date: 2021/8/4 16:59
 */
@Data
public class TicketValidationDTO implements Serializable {
    private Boolean validation;
    private String ticket;
    private Long id;

    public TicketValidationDTO(boolean validation, String ticket, Long id) {
        this.validation = validation;
        this.ticket = ticket;
        this.id = id;
    }

    public TicketValidationDTO() {
    }
}
