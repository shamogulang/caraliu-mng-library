package cn.caraliu.common;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jeffchan 2020/03/28
 */
@Data
@NoArgsConstructor
public class PageInfo {

    private int pageNum;
    private int pageSize;
    private int total;

}
