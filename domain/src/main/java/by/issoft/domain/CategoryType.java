package by.issoft.domain;

public enum  CategoryType {
    ANIMAL(1),
    ARTIST(2),
    BOOK(3);
    private int value;
    private CategoryType(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
    public boolean Compare(int i){return value == i;}
    public static CategoryType GetValue(int value)
    {
        CategoryType[] categoryTypes = CategoryType.values();
        for(int i = 0; i < categoryTypes.length; i++)
        {
            if(categoryTypes[i].Compare(value))
                return categoryTypes[i];
        }
        throw new IllegalArgumentException();
    }
}
