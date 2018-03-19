/**
 * 
 */
package cn.e3mall.pojo;

import java.io.Serializable;
import java.util.List;

/**
 *
 *<p>Title:EasyUIDataGridResult</p>
 *<p>Description:</p>
 *
 * @author chenfq
 * date 2018年2月28日 下午6:09:12
 */
public class EasyUIDataGridResult implements Serializable{
	
	private Long total;
	private List rows;
	/**
	 * @return the total
	 */
	public Long getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(Long total) {
		this.total = total;
	}
	/**
	 * @return the rows
	 */
	public List getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(List rows) {
		this.rows = rows;
	}
	
	
}
