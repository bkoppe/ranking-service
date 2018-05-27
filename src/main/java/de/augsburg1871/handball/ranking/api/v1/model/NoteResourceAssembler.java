package de.augsburg1871.handball.ranking.api.v1.model;

import de.augsburg1871.handball.ranking.api.v1.RankingController;
import de.augsburg1871.handball.ranking.persistence.model.Note;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class NoteResourceAssembler extends ResourceAssemblerSupport<Note, NoteResource> {

    public NoteResourceAssembler() {
        super(RankingController.class, NoteResource.class);
    }

    @Override
    public NoteResource toResource(final Note entity) {
        return new NoteResource(entity);
    }

}
