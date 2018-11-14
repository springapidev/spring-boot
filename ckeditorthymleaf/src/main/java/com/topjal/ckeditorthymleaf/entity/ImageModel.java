package com.topjal.ckeditorthymleaf.entity;
import javax.persistence.*;

@Entity
@Table(name="image_model")
public class ImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "size")
    private long size;

    @Lob
    @Column(name="pic")
    private byte[] pic;

    public ImageModel(){}

    public ImageModel(Long id, String name, String type, int size, byte[] pic) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.size = size;
        this.pic = pic;
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }

    public byte[] getPic(){
        return this.pic;
    }

    public void setPic(byte[] pic){
        this.pic = pic;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}