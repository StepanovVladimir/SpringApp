package com.example.springapp.models;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
public class Post
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Database generated post id")
    private Long id;

    @ApiModelProperty(value = "Title of post",
            example = "South Korea has created a wearable printer for printing electronics on the skin")
    private String title;

    @Lob
    @ApiModelProperty(value = "Anons of post",
            example = "Korean engineers showed the 3D printer action in a video. It demonstrates how a template is set, printed, and ready-made sensors work. The device will be presented in September at the UIST 2020 conference.")
    private String anons;

    @Lob
    @ApiModelProperty(value = "Full text of post",
            example = "The device consists of a frame and a print head — a small motor and a syringe with ink. Before starting work, the skin must be smeared with an insulating gel, and then attach the device to it with two belts. Next, a special program draws a diagram of the future sensor and sets the settings.")
    private String fullText;

    public Post() {}

    public Post(String title, String anons, String fullText)
    {
        this.title = title;
        this.anons = anons;
        this.fullText = fullText;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getAnons()
    {
        return anons;
    }

    public void setAnons(String anons)
    {
        this.anons = anons;
    }

    public String getFullText()
    {
        return fullText;
    }

    public void setFullText(String fullText)
    {
        this.fullText = fullText;
    }
}
