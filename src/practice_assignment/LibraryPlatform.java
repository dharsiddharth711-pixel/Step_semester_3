package practice_assignment;

import java.util.*;

abstract class Resource {
    protected String id;

    public Resource(String id) {
        this.id = id;
    }

    public String getId() { return id; }
}

interface Reservable {
    void reserve(String memberId);
}

interface Downloadable {
    void download();
}

class BookResource extends Resource implements Reservable {
    public BookResource(String id) { super(id); }

    @Override
    public void reserve(String memberId) {
        System.out.println(id + " reserved for " + memberId);
    }
}

class EBookResource extends Resource implements Downloadable {
    public EBookResource(String id) { super(id); }

    @Override
    public void download() {
        System.out.println(id + " downloaded");
    }
}

public class LibraryPlatform {
    private List<Resource> resources = new ArrayList<>();

    public void addResource(Resource r) {
        for (Resource existing : resources) {
            if (existing.getId().equals(r.getId())) {
                System.out.println("duplicate rejected");
                return;
            }
        }
        resources.add(r);
        // Keep array ordered by ID
        resources.sort(Comparator.comparing(Resource::getId));
        System.out.println(r.getId() + " added");
    }

    public Resource findResource(String id) {
        for (int i = 0; i < resources.size(); i++) {
            if (resources.get(i).getId().equals(id)) {
                System.out.println(id + " found at index " + i);
                return resources.get(i);
            }
        }
        System.out.println(id + " not found");
        return null;
    }

    public void reserveResource(String id, String memberId) {
        Resource r = findResourceById(id);
        if (r instanceof Reservable) {
            ((Reservable) r).reserve(memberId);
        } else if (r != null) {
            System.out.println(id + " rejected: reserve unsupported");
        }
    }

    public void downloadResource(String id) {
        Resource r = findResourceById(id);
        if (r instanceof Downloadable) {
            ((Downloadable) r).download();
        } else if (r != null) {
            System.out.println(id + " rejected: download unsupported");
        }
    }

    private Resource findResourceById(String id) {
        for (Resource r : resources) {
            if (r.getId().equals(id)) return r;
        }
        return null;
    }

    public static void main(String[] args) {
        LibraryPlatform library = new LibraryPlatform();

        library.addResource(new BookResource("B1"));
        library.addResource(new BookResource("B1"));
        library.addResource(new EBookResource("E1"));

        library.reserveResource("B1", "M1");
        library.downloadResource("B1");
        library.downloadResource("E1");

        library.findResource("E1");
    }
}
