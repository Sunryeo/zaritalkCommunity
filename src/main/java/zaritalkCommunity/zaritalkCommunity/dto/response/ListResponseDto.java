package zaritalkCommunity.zaritalkCommunity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
@AllArgsConstructor
public class ListResponseDto<T> {
    private T data;
    private int count;
}
