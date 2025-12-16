# CSC120-FinalProject

## Deliverables:
 - Your final codebase
 - Your revised annotated architecture diagram
 - Design justification (including a brief discussion of at least one alternative you considered)
 - A map of your game's layout (if applicable)
 - `cheatsheet.md`
 - Completed `rubric.md`


## Additional Reflection Questions
 - What was your **overall approach** to tackling this project?

Our approach was to first decide which basic structures needed to exist in the game, and then develop the details later on. For example, some of our first code included Grabbable and Location classes to establish that our game would have items and locations with which the player could interact. We didn't decide on the exact subclasses of items, or on the full layout of rooms in the game, until we were almost done.

 - What **new thing(s)** did you learn / figure out in completing this project?

We learned how to use GitHub in more sophisticated ways than we previously had, especially with regard to collaboration. At first, we were all trying to work in the main branch of our repository, but this caused issues when pushing. We eventually learned to work in separate branches and merge them with main. Another thing we had to figure out was how to write the main method where the game happens, which at first felt very bulky and messy. We found that this part of the code was more manageable if we shortened it by moving some parts to other methods. For instance, instead of checking in the main loop to see if the player is holding a food item, we can have that check occur in the method called to eat the food.

 - Is there anything that you wish you had **implemented differently**?

We wish we had made the relationship between the Flashlight and Battery classes more realistic. When we first created Flashlight and wrote many parts of the game to depend on the flashlight battery level, we weren't thinking yet about Battery. Instead of giving the Flashlight a Battery attribute, we gave it an int to represent its battery level. Now, when the player changes the flashlight battery, we just update this int value and remove the fresh battery from the player's inventory, instead of being able to replace an actual Battery object in the Flashlight. This setup reduces the functionality - for example, the player can't put the old battery back in once they change it - but it would have been complicated to redo once we realized our mistake.

 - If you had **unlimited time**, what additional features would you implement?

With unlimited time, we would make a much larger cave with more rooms and items hidden throughout. Making rooms is time consuming since each one needs individual messages associated with it. We would also find ways to make the monsters more engaging, since currently they are very predictable and simply wait to be killed. One idea is to make it so that monsters can suddenly pop up in a room at random. We could also make the game more believable by having monsters block passages - in the current version, they only guard objects, but don't obstruct the player's movement.

 - What was the most helpful **piece of feedback** you received while working on your project? Who gave it to you?

Our classmate Grace gave us some very helpful feedback about the formatting of the printed messages in our game. She noticed that the text was difficult to read and suggested we space it out more, instead of printing big paragraphs. We implemented this feedback and found that it made the game much more user friendly. Until this point, we hadn't thought much about formatting, so Grace's advice led us to consider the player's experience of the game and make other changes, such as printing some bulleted lists.

 - If you could go back in time and give your past self some **advice** about this project, what hints would you give?

We would advise our past selves to be thoughtful about how many classes we truly need, and to make sure each class we create actually requires some different functionality than those we already have. Additionally, we would point out that multiple classes can sometimes be combined into one through the addition of an attribute (for example, the boolean isOutside allows inside and outside locations to be members of the same class). While doing this project, we ended up dropping many classes that we initially planned to have, which made for more efficient code.

 - _If you worked with a team:_ please comment on how your **team dynamics** influenced your experience working on this project.

Working in a team helped us stay organized while completing the project. We would meet to discuss our progress and assign specific tasks to each person, so that once we were starting our independent work, we already had a clear plan. Teamwork also streamlined the experience of this project by allowing us each to focus on specific aspects of the game, rather than juggling all of them. 