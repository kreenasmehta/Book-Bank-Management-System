package BookBankSystem.util;

/**
 * Created by kreenamehta on 11/29/16.
 * Book Genre
 */
public enum BookGenre {


    scienceFiction("science fiction"),
    satire("satire"),
    drama("drama"),
    actionAdventure("action and adventure"),
    romance("romance"),
    mystery("mystery"),
    horror("horror"),
    travel("travel"),
    kids("kids"),
    history("history"),
    math("math"),
    comic("comic"),
    cookbook("cookbook"),
    biography("biography"),
    autobiography("autobiography"),
    health("health"),
    religionSpriritual("religion and spiritual"),
    encyclopedia("encyclopedia"),
    dictionary("dictionary");


    private String bookGenre;

    BookGenre(String bookGenre){
        this.bookGenre = bookGenre;
    }

    public String getBookGenre(){
        return this.bookGenre;
    }

    public static BookGenre fromString(String bookGenre){
        if(bookGenre != null){
            for(BookGenre genre: BookGenre.values()){
                if(bookGenre.equalsIgnoreCase(genre.toString())){
                    return genre;
                }
            }
        }
        return null;
    }


}
