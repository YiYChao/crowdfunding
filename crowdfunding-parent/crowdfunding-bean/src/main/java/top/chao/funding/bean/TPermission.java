package top.chao.funding.bean;

import java.util.ArrayList;
import java.util.List;

public class TPermission {
    private Integer id;

    private Integer pid;

    private String name;

    private String icon;

    private String url;
    
    private boolean open = true;		// 默认展开
    
    private boolean checked = false;	// 默认未被选中

    private List<TPermission> children = new ArrayList<TPermission>();
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public List<TPermission> getChildren() {
		return children;
	}

	public void setChildren(List<TPermission> children) {
		this.children = children;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
    
}