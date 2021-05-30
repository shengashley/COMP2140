import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;


public class LastNameFirstNameA1Q1 {
    private static ArrayList<Book> books = new ArrayList<Book>();

    static class Book{
       String bookTitle;
       String authorFirstName;
       String authorLastName;
       Boolean isLoan;
    }

    static class Library{

        Library(){};

        void addBook(Book book){

             if(books.isEmpty())
                 books.add(book);
             else{

                   int insertPosition = insertBookPosition(book);

                   if(insertPosition >= 0)
                       books.add(insertPosition,book);
             }
        }

        int insertBookPosition(Book book){

           for(int i = 0 ; i < books.size() ; i++){
               int result [] = compareLastName(book.authorLastName,books.get(i).authorLastName);
               /** book author last name alphabetical order prior to the one of books[i]**/
               if(result[1]==0) {
                   return i;

               } else if(result[1]==1) {
                   if(i == books.size()-1)
                       return i+1;
               }
               /**Two last name strings are same alphabetical order,
                *then compare which has shorter string length.
                * If two string are same length then do first name comparision**/
               else{
                    if(result[0]==0){
                        return i;
                    }else if(result[0]==1){
                        return i+1;
                    }else{
                        result = compareFirstName(book.authorFirstName,books.get(i).authorFirstName);
                        if(result[1]==0) {
                            return i;
                        }else if(result[1]==1) {
                            if(i == books.size()-1)
                                return i+1;
                        }
                        /**Two first name strings are same alphabetical order,
                         *then compare which has shorter string length.
                         * If two string are same length then do title comparision**/
                        else{
                            if(result[0]==0){
                                return i;
                            }else if(result[0]==1) {
                                return i + 1;
                            }
                            else{
                                result = compareBookTitle(book.bookTitle,books.get(i).bookTitle);
                                if(result[1]==0) {
                                    return i;
                                } else if(result[1]==1) {
                                    if(i == books.size()-1)
                                        return i+1;
                                }else{
                                    if(result[0]==0){
                                        return i;
                                    }else if(result[0]==1) {
                                        return i + 1;
                                    }else{
                                        return i;
                                    }
                                }

                            }
                        }
                    }

               }

           }
           return -1;
        }

        int [] compareLastName(String authorLastName1 ,String authorLastName2){
            return bookInformComparison(authorLastName1,authorLastName2);

        }
        int [] compareFirstName(String authorFirstName1 ,String authorFirstName2){
            return bookInformComparison(authorFirstName1,authorFirstName2);

        }
        int [] compareBookTitle(String bookTitleName1 ,String bookTitleName2){
            return bookInformComparison(bookTitleName1,bookTitleName2);
        }
        int[] bookInformComparison(String str1, String str2){
            int stringLength = 0;
            /**
             * int[0]:store which string has shorter length (0:str1 , 1:str2 , 2 : same)
             * int[1]:store which string has prior alphabetical order (0:str1, 1:str2 , 2:same)
             * (a < b < c < d <...< z)
             **/
            int result []= new int [2];

            if(str1.length() < str2.length()) {
                stringLength = str1.length();
                result[0] = 0;
            }else if (str1.length() > str2.length()){
                stringLength = str2.length();
                result[0] = 1;
            }else{
                stringLength = str1.length();
                result[0] = 2;
            }

            for(int i = 0; i < stringLength ;i++){
                if(str1.charAt(i) > str2.charAt(i)){
                    result[1]= 1 ;
                    return result;

                }else if(str1.charAt(i) < str2.charAt(i)){
                    result[1]= 0;
                    return result;
                }
            }

            result[1] = 2;
            return result;
        }

        void listByAuthor(String authorLastName){
            if(books.size()>0){
                System.out.println("Books by "+authorLastName+" :");
                authorLastName = authorLastName+",";
               for(int i = 0 ; i < books.size(); i++){
                   if(books.get(i).authorLastName.equals(authorLastName)){
                       System.out.println(books.get(i).authorLastName+" "+
                               books.get(i).authorFirstName+" "+books.get(i).bookTitle);
                   }
               }
            }else{
                System.out.println("No book in the library.");
            }
        }

        void listByTitle(String bookTitle){
            if(books.size()>0){
                System.out.println("Books named "+bookTitle+":");
                for(int i = 0; i < books.size(); i++){
                    if(books.get(i).bookTitle.equals(bookTitle)){
                        System.out.println(books.get(i).authorLastName+" "+
                                books.get(i).authorFirstName+" "+books.get(i).bookTitle);
                    }
                }
            }else{
                System.out.println("No book in the library.");
            }
        }

        void loanBook(String authorLastName, String authorFirstName, String bookTitle){
            if(books.size()>0){
                System.out.println("Books loaned :");

                for(int i = 0 ; i < books.size(); i++){
                    if(books.get(i).authorLastName.equals(authorLastName)
                       && books.get(i).authorFirstName.equals(authorFirstName)
                       && books.get(i).bookTitle.equals(bookTitle)
                       && books.get(i).isLoan)
                    {
                       books.get(i).isLoan = false;
                       System.out.println(books.get(i).authorLastName+" "+
                                books.get(i).authorFirstName+" "+books.get(i).bookTitle);
                      break;
                    }
                }
            }else{
                System.out.println("No book in the library.");
            }
        }

        void returnBook(String authorLastName, String authorFirstName, String bookTitle){
            if(books.size()>0){
                System.out.println("Book returned :");

                for(int i = 0 ; i < books.size(); i++){
                    if(books.get(i).authorLastName.equals(authorLastName)
                       && books.get(i).authorFirstName.equals(authorFirstName)
                       && books.get(i).bookTitle.equals(bookTitle)
                       && !books.get(i).isLoan)
                    {
                       books.get(i).isLoan = true;
                       System.out.println(books.get(i).authorLastName+" "+
                                books.get(i).authorFirstName+" "+books.get(i).bookTitle);
                        break;
                    }
                }
            }else{
                System.out.println("No book in the library.");
            }
        }

    }

    public static void main(String[] args) {
         try {
             System.out.println("Please enter the input file name (.txt files only):");


             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
             File file = new File(bufferedReader.readLine());
             FileReader fileReader = new FileReader(file);

             bufferedReader = new BufferedReader(fileReader);

             String line;
             LinkedList<String[]> lineList = new LinkedList<String[]>();

             while((line = bufferedReader.readLine())!= null){
                  String [] temp = line.split(" ");
                  String [] lineArray;

                  if(temp[0].equals("SEARCHA"))
                      lineArray = temp;
                  else
                      lineArray = parseInput(temp);

                  lineList.add(lineArray);
             }

             System.out.println();
             System.out.println("Processing file "+file+"... ");
             System.out.println();

             Library library = new Library();
             for(int i = 0; i < lineList.size(); i++){
                 Book mBook = new Book();
                 if(lineList.get(i)[0].equals("ADD")){
                     mBook.authorLastName = lineList.get(i)[1];
                     mBook.authorFirstName = lineList.get(i)[2];
                     mBook.bookTitle = lineList.get(i)[3];
                     mBook.isLoan = true;
                     library.addBook(mBook);
                 }else if(lineList.get(i)[0].equals("SEARCHA")){
                     library.listByAuthor(lineList.get(i)[1]);
                     System.out.println();
                 }else if(lineList.get(i)[0].equals("SEARCHT")){
                     library.listByTitle(lineList.get(i)[1]);
                     System.out.println();
                 }else if(lineList.get(i)[0].equals("GETBOOK")){
                     library.loanBook(lineList.get(i)[1],lineList.get(i)[2],lineList.get(i)[3]);
                     System.out.println();
                 }else if(lineList.get(i)[0].equals("RETURNBOOK")) {
                     library.returnBook(lineList.get(i)[1], lineList.get(i)[2], lineList.get(i)[3]);
                     System.out.println();
                 }
             }

             /*System.out.println();
             System.out.println();
             System.out.println();

             for(int i = 0 ; i < books.size(); i++){
                 System.out.print(books.get(i).authorLastName);
                 System.out.print(books.get(i).authorFirstName);
                 System.out.print(books.get(i).bookTitle);
                 System.out.println();
             }*/

            System.out.println("Program terminated normally.");
         }catch(IOException e){
             e.printStackTrace();
         }


    }

     static String [] add (String [] temp){
         String [] result = new String [4];
         if(temp[0].equals(" "))
             result[0] = "unknown,";
         else
             result[0] = temp[0];
         if (temp[1].equals(" "))
             result[0] = "unknown,";
         else
             result[1] = temp[1];
         if(temp[2].equals(" ")|| temp[2].equals(",")){
             result[2] = "unknown,";
         }else
             result[2] = temp[2];
         String title = "";
         for(int i = 3 ; i < temp.length ;i ++){
            title = title + temp[i];
            title = title + " ";
         }
         result[3] = title;
         return result;
     }

    static String [] getBookOrReturnBook (String [] temp){
        String [] result = new String [4];
        result[0] = temp[0];
        result[1] = temp[1];
        result[2] = temp[2];
        String title = "";
        for(int i = 3 ; i < temp.length ;i ++){
            title = title + temp[i];
            title = title + " ";
        }
        result[3] = title;
        return result;
    }

     static String [] searcht(String [] temp){
        String [] result = new String [2];
        result[0] =temp[0];
        String title ="";
         for(int i = 1 ; i < temp.length ;i ++){
             title = title + temp[i];
             title = title + " ";
         }

         result[1] = title;
         return result;
     }


     static String [] parseInput(String [] temp){

            if(temp[0].equals("ADD")){
                return add(temp);
            }else if(temp[0].equals("SEARCHA")){
                return temp;
            }else if(temp[0].equals("GETBOOK")){
                return getBookOrReturnBook(temp);
            }else if(temp[0].equals("SEARCHT")){
                 return searcht(temp);
            }else if(temp[0].equals("RETURNBOOK")){
                 return getBookOrReturnBook(temp);
            }
            String result[] = new String[3];
            return result;
     }


}
