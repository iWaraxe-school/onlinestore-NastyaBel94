package by.issoft.domain;

import by.issoft.domain.categories.AnimalCategory;
import by.issoft.domain.categories.ArtistCategory;
import by.issoft.domain.categories.BookCategory;

import java.util.Objects;

public class CategoryFactory {
    public Categories getCategory(CategoryType type){
        if(Objects. equals(type, CategoryType.ANIMAL)){
            return new AnimalCategory();
        }
        else if (Objects.equals(type, CategoryType.ARTIST)){
            return  new ArtistCategory();
        }
        else if (Objects.equals(type, CategoryType.BOOK)){
            return  new BookCategory();
        }
        else return null;
        }
    }

