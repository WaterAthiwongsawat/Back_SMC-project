package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer EqId;
    private String EqUrl;
    private String EqName;
    private int quantity; // จำนวน
    private int remaining; // คงเหลือ

    public Equipment() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Equipment(Integer EqId, String EqUrl, String EqName, int quantity, int remaining) {
        super();
        this.EqId = EqId;
        this.EqUrl = EqUrl;
        this.EqName = EqName;
        this.quantity = quantity;
        this.remaining = remaining;
    }

    public Integer getEqId() {
        return EqId;
    }

    public void setEqId(Integer EqId) {
        this.EqId = EqId;
    }

    public String getEqUrl() {
        return EqUrl;
    }

    public void setEqUrl(String EqUrl) {
        this.EqUrl = EqUrl;
    }

    public String getEqName() {
        return EqName;
    }

    public void setEqName(String EqName) {
        this.EqName = EqName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }
}
