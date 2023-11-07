package book_store.controller;

import java.util.Scanner;
import book_store.dao.BookCrud;
import book_store.dao.UserCrud;
import book_store.dto.Book;
import book_store.dto.User;

public class Book_store_Main {

	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		BookCrud crud=new BookCrud();
		UserCrud crud1=new UserCrud();
		crud.createTable();
		crud1.createTable();
		boolean check=true;
		do {
			System.out.println("Welcome to the BookStore....");
			System.out.println("enter the choice \n 1.book 2.user 3.exit");
			int choice=sc.nextInt();
			switch(choice) {
			case 1:{
				boolean check2=true;
				do {
					System.out.println("choose options \n 1.save \n 2.update \n 3.delete \n 4.exit");
					int choicebook=sc.nextInt();
					switch(choicebook) {
					case 1:{
						System.out.println("enter the id");
						int id=sc.nextInt();
						System.out.println("enter the bookname");
						String name=sc.next();
						System.out.println("enter the author");
						String author=sc.next();
						System.out.println("enter the price");
						double price=sc.nextDouble();
						System.out.println("enter the language");
						String language=sc.next();
						Book book=new Book(id, name, author, price, language);
						crud.saveBook(book);
						break;
					}
					case 2:{
						System.out.println("enter the id");
						int id=sc.nextInt();
						System.out.println("enter the price");
						double price=sc.nextDouble();
						crud.updateBook(price, id);
						break;
					}
					case 3:{
						System.out.println("enter the id");
						int id=sc.nextInt();
						crud.delete(id);
						break;
					}
					case 4:{
						check2=false;
						break;
					}
					}
				}while(check2);
			}
			case 2:{
				boolean check3=true;
				do {
					System.out.println("enter the choice \n 1.newuser \n 2.olduser \n 3.exit");
					int choiceuser=sc.nextInt();
					switch(choiceuser) {
					case 1:{
						System.out.println("enter the id");
						int id=sc.nextInt();
						System.out.println("enter the name");
						String name=sc.next();
						System.out.println("enter the phno");
						Long phno=sc.nextLong();
						System.out.println("enter the amount you have");
						Double amount=sc.nextDouble();
						User user=new User(id, name, phno, amount);
						crud1.saveUser(user);
						break;
					}
					case 2:{
						System.out.println("enter your id");
						int id=sc.nextInt();
						User user=new User();
						user.setId(id);
						System.out.println("user details");
						crud1.fetch(user);
						System.out.println();
						System.out.println("Books available");
						crud.fetch(null);
						System.out.println();
						System.out.println("Select the book_id which you want to purchase");
						int b_id=sc.nextInt();
						Book book=new Book();
						book.setId(b_id);
						crud.receipt(book,user);
						break;
					}
					case 3:{
						check3=false;
					}
					}
				}while(check3);
			}
			case 3:{
				check=false;
			}
			}
		}while(check);
		System.out.println("Thanks visit again.....!");
	}
}














