import random

FIRST_NAMES = """John Bob Fred Chris Betty Jessica Judy Michelle
Rachel Bruce Ben Christina James Jack Jill Katie Lily
Harry Isabel Thomas Hannah Michael Ralph Felix Rory Sam
Tristan Jarrod Lucy Zelda Robert Sean Richard Matthew Andrew""".split()
SECOND_NAMES = """Jones Smith White Stevens Roper Merry Spencer Wentzel
Nelson French Noah Nell George Hayden McDougall Alexander Gallon
Gardner Davies Isaacs Kisten Corbellari Els Darries Nel
Wiese Cameron Smit Swart Evans Badenhorst Myburgh Viljoen
Swanepoel Pollard Conradie Visser Vermeulen Visagie
Greyling Joubert""".split()

FIXED_NAMES = ["Chad le Clos", "Usain Bolt", "Wayde van Niekerk", "Natalie Du Toit",
               "Ryk Neethling", "Katie le Decky", "Michael Phelps",
               "Sunette Viljoen", "Caster Semenya", "Akani Simbine",
               "Godfrey Mokoena", "Luvo Manyonga"]

TIME_RANGE = [15000, 20000]

for oper in [0, 1, 2, 3]:
    for target in [10, 20, 30, 40, 50, 100]:
        for diff in [1, 2, 3, 4]:
            file = open(str(oper) + " " + str(target) + " " + str(diff) + ".txt", "w")
            
            data = ""

            times = [0, 0, 0, 0, 0, 0, 0, 0]

            for i in range(8):
                times[i] = random.randint(TIME_RANGE[0] * target / 10, TIME_RANGE[1] * target / 10)
            times.sort()
            
            for i in range(8):
                
                data += str(times[i])
                data += " "

                name = ""
                if random.randint(1, 2) == 1:
                    name += FIXED_NAMES[random.randint(0, len(FIXED_NAMES) - 1)]
                else:
                    name += FIRST_NAMES[random.randint(0, len(FIRST_NAMES) - 1)]
                    name += " "
                    name += SECOND_NAMES[random.randint(0, len(SECOND_NAMES) - 1)]

                data += name

                data += "\n"

            file.write(data)

            file.close();
