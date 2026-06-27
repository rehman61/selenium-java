package pages.api.githubModel;

public class RepoModel {
    private String name;
    private String description;
    private boolean isPrivate;

    public RepoModel(String name, String description, boolean isPrivate) {
        this.name = name;
        this.description = description;
        this.isPrivate = isPrivate;
    }
}
