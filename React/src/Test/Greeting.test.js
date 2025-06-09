import Greeting from "./Greetings";
import {render,screen} from'@testing-library/react';
import userEvent from "@testing-library/user-event";


describe('Greeting component' , () =>{
    test('renders Hello World' ,() => {

        //Arrange
        render(<Greeting/>)
    
        //Act --(Button clicking)
    
        //Assert
        const hello = screen.getByText('Hello World',{exact :false});
        expect(hello).toBeInTheDocument();
    
        
    });
    
    //It will fail becox Hello World!
    test('renders Hello World with Exact' ,() => {
    
        //Arrange
        render(<Greeting/>)
    
        //Act --(Button clicking)
    
        //Assert
        const hellowithexact = screen.getByText('Hello World !');
        expect(hellowithexact).toBeInTheDocument();
        
    });

    test('renders good to see you if the button was Not clicked' ,() => {
    
        render(<Greeting/>)
    
        
        const outputElement = screen.getByText('good to see you', {exact:false})
        expect(outputElement).toBeInTheDocument();
        
    });

    test('renders "Changed!" if the button was clicked' ,() => {
    
        render(<Greeting/>)
    
        //Act --(Button clicking)
        const buttonElement = screen.getByRole('button');
        userEvent.click(buttonElement);

        //Assert
        const outputElement = screen.getByText('Changed!', {exact:false})
        expect(outputElement).toBeInTheDocument();
        
    });

    test('does not render "good to see you"  if the button was clicked' ,() => {
    
        render(<Greeting/>)
    
        //Act --(Button clicking)
        const buttonElement = screen.getByRole('button');
        userEvent.click(buttonElement);

        //Assert
        const outputElement = screen.queryByText('good to see you', {exact:false})
        expect(outputElement).toBeNull();
        
    });
    
})
