package com.shun.blog.model.menu;

import com.shun.blog.model.common.AbstractCommon;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Data
@Entity(name="TB_MENU")
public class Menu extends AbstractCommon implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MENU_ID")
	private Long menuId;
	
	@Column(name = "MENU_NAME", length=30, nullable = false)
	private String menuName;
	
	//admin , index 등 다양한 종류.
	@Column(name = "MENU_TYPE", length=30, nullable = false)
	private String menuType;

	@Column(name = "MENU_URL", length=80)
	private String menuUrl;
	
	@Column(name = "MENU_ORDER", length=10, nullable = false)
	private int menuOrder;
	
	@Column(name = "MENU_DEPTH", length=10, nullable = false)
	private int menuDepth;
	
	@Column(name = "MENU_PARENT_ID", length=10, nullable = true)
	private Long menuParentId;

	@Transient
	private int type;
	
	@Transient
	private List<Menu> submenuList;

    public Menu(){
		
	}
	
	public Menu(int menuDepth){
		this.menuDepth=menuDepth;
	}
	
	public Menu(int menuDepth, String menuType){
		this.menuDepth=menuDepth;
		this.menuType=menuType;
	}
}
