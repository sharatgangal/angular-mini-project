export class Book{
    _id:string;
    userId:string;
    bookId:string;
    bookTitle:string;
    bookAuthor:any;
    bookImage:string;
    bookFrequency:any;
    bookReadMore:any;


    public setUserId(userId)
    {
        console.log(userId);
        
        this.userId=userId;
    }

    public getUserId():string
    {
        return this.userId;
    }

    
}