package net.codescale.util.imdb;


public class Movie extends Object {

    public static enum DataSource {
        HTTP_REQUEST,
        FILESYSTEM
    }

    protected String data;
    protected DataSource dataSource;

    protected int id;
    protected String title;
    protected String year;
    protected String plot;


    public Movie setData(String data) {
        this.data = data;
        return this;
    }

    public Movie setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    public Movie setId(int id) {
        this.id = id;
        return this;
    }

    public Movie setTitle(String title) {
        this.title = title;
        return this;
    }

    public Movie setYear(String year) {
        this.year = year;
        return this;
    }

    public Movie setPlot(String plot) {
        this.plot = plot;
        return this;
    }


    public String toString() {
        if (this.title != null && this.year != null) {
            return String.format("%s (%s)", this.title, this.year);
        } else {
            return "";
        }
    }
}
