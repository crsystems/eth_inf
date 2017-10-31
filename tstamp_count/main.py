import time
import datetime
import os

print("TimeStamp generating counting tool for slow lessons")
print("Version: 0.02")

event = input("Enter the name for the tracking file: ")

decision = input("To start counting press s or c to abort: ")

if(decision != "s"):
    print("quitting...")
    quit()

num = 0
start_tstamp = str(datetime.datetime.now())
start_time = float(time.time())
times = [start_time]

print("Starttime: " + start_tstamp + "\n\n")

character = str(input("Press any key to count an event or c to end counting: "))

while(character != 'c'):
    if(character == 'd'):
        times.pop()
        num = num - 1
        print("Deleted last entry")
    else:
        print("+1")
        print("Time: " + str(datetime.datetime.now()))
        times.append(float(time.time()))
        num = num + 1
        print("Time since last event: " + str(times[num]-times[num-1]))
    print("Counted events: " + str(num))
    character = input("Press any key to count an event, d to delete the last entry or c to end counting: ")

session = open(event, "w+")
session.write(str(start_time))
session.write("\n")

for items in times:
    if(items-start_time != 0):
        print(str(items-start_time))
        session.write(str(items-start_time))
        session.write("\n")


session.close()

print("Total counted entries: " + str(num))
quit()
