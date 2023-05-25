package Domain;

public class Books {

        private int bookId;
        private int categoryId;

        private String categoryName;
        private int bookISBN;
        private String bookTitle;
        private String bookAuthor;
        private double bookPrice;
        private String bookPublisher;
        private int bookRating;
        private String bookImage;

        public int getBookId() {
            return bookId;
        }

        public void setBookId(int bookId) {
            this.bookId = bookId;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public int getBookISBN() {
            return bookISBN;
        }

        public void setBookISBN(int bookISBN) {
            this.bookISBN = bookISBN;
        }

        public String getBookTitle() {
            return bookTitle;
        }

        public void setBookTitle(String bookTitle) {
            this.bookTitle = bookTitle;
        }

        public String getBookAuthor() {
            return bookAuthor;
        }

        public void setBookAuthor(String bookAuthor) {
            this.bookAuthor = bookAuthor;
        }

        public double getBookPrice() {
            return bookPrice;
        }

        public void setBookPrice(double bookPrice) {
            this.bookPrice = bookPrice;
        }

        public String getBookPublisher() {
            return bookPublisher;
        }

        public void setBookPublisher(String bookPublisher) {
            this.bookPublisher = bookPublisher;
        }

        public int getBookRating() {
            return bookRating;
        }

        public void setBookRating(int bookRating) {
            this.bookRating = bookRating;
        }

        public String getBookImage() {
            return bookImage;
        }

        public void setBookImage(String bookImage) {
            this.bookImage = bookImage;
        }
}


