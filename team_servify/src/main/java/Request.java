import java.math.BigDecimal;
import java.util.Date;

public class Request {
    private Long userId; // so user id or client id? Only client makes requests. Manager just approves...
    private Detail detail; // need to make it a list
    private Date requestDate;
    private Date systemResponseDate;
    private Date managerResponseDate; // do we need this? Or just one final interaction date?
    private BigDecimal priceInitial; // gen by system
    private BigDecimal priceManager; // overriden by manager
    private BigDecimal priceFinal;
    private RequestStatus requestStatus;
    private BigDecimal price;
    private String notes;
}
