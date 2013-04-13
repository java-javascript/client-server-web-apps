// Define the Dog class
function Dog() {
    Animal.call(this); // Call the parent constructor
}

// Dog inherits from Animal
Dog.prototype = new Animal();

// Update the constructor to match the new class
Dog.prototype.constructor = Dog;

// Replace the speak method
Dog.prototype.speak = function(){
    return "woof";
}