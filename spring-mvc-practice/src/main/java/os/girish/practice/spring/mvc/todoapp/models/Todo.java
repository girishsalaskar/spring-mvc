package os.girish.practice.spring.mvc.todoapp.models;

import java.util.Date;

import javax.validation.constraints.Size;

public class Todo {

	private int id;
	private String user;
	
	@Size(min=5, message="Please enter minimum 5 characters!")
	private String desc;
	
	private Date target;
	private boolean isDone;

	/**
	 * @param id
	 * @param user
	 * @param desc
	 * @param target
	 * @param isDone
	 */
	public Todo(int id, String user, String desc, Date target, boolean isDone) {
		super();
		this.id = id;
		this.user = user;
		this.desc = desc;
		this.target = target;
		this.isDone = isDone;
	}

	public Todo() {
		
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the target
	 */
	public Date getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(Date target) {
		this.target = target;
	}

	/**
	 * @return the isDone
	 */
	public boolean isDone() {
		return isDone;
	}

	/**
	 * @param isDone the isDone to set
	 */
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Todo other = (Todo) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", user=" + user + ", desc=" + desc + ", target=" + target + ", isDone=" + isDone
				+ "]";
	}
}
