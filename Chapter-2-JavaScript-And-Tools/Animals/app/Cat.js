// Define the Cat
function Cat() {
    Animal.call(this);
}

// Set the object's prototype
Cat.prototype = new Animal();

// Name the constructor in a manner to suit the class
Cat.prototype.constructor = Cat;

// Create a class specific implementation
Cat.prototype.speak = function(){
    return "meow";
}