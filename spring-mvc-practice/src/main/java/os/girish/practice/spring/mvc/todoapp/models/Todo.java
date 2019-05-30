package os.girish.practice.spring.mvc.todoapp.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="todos")
public class Todo {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String user;

	@Column(name="note")
	@Size(min = 5, message = "Please enter minimum 5 characters!")
	private String desc;

	private Date target;
	
	@Column(name="is_done")
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
		return String.format("Todo [id=%s, user=%s, desc=%s, target=%s, isDone=%s]", id, user, desc, target,
				isDone);
	}
}
