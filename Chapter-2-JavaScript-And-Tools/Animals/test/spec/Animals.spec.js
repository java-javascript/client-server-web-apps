// Animal Test - using beforeEach
describe("Animal", function() {

    beforeEach(function() {
         animal = new Animal();

    });

    it("should be able to speak", function() {
        expect(animal.speak()).toEqual("Animal is speaking.");
    });
});

// Dog inherits from animal and overrides the speak method.  Uses a function scoped variable for testing
describe("Dog", function() {

    it("should be able to speak", function() {
        var dog = new Dog();
        expect(dog.speak()).toEqual("woof");
    });

});

// A bit more terse - a cat that inherits from animal.  Object constructor called in the same line as the speak method.
describe("Cat", function() {

    it("should be able to speak", function() {
        expect((new Cat()).speak()).toEqual("meow");
    });

});

