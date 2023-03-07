import random;
import time;

class Bubble:
    length = 10000
    numbers = [None]*length
    for i in range(length):
        numbers[i] = random.randint(0,4*length-1)
        
    start_time = time.time()
    for i in range(length,-1,-1):
        for j in range(1,i,1):
            if(numbers[j-1] > numbers[j]):
                tmp = numbers[j-1]
                numbers[j-1] = numbers[j]
                numbers[j] = tmp

    print(time.time() - start_time)
    file = open("pythonsort.txt","w+")
    for line in numbers:
        file.write(str(line) + " ")