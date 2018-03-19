/**
 * 
 */
package cn.e3mall.pojo;

import java.io.Serializable;

/**
 *
 *<p>Title:EasyUITreeNode</p>
 *<p>Description:</p>
 *
 * @author chenfq
 * date 2018年3月1日 下午4:04:15
 */
public class EasyUITreeNode implements Serializable{
	
	private long id;
	private String text;
	private String state;
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	
}
