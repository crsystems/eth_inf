prompt = 'Please enter the filename of the logfile to be parsed: ';
data = [zeros zeros];
count = 0;
filename = input(prompt);
file_handle = fopen(filename, "r");


timestamp = fgetl(file_handle);
disp(timestamp);
temp = fgetl(file_handle);
while temp ~= -1
    count = count + 1;
    data = [data; str2num(temp) count];
    temp = fgetl(file_handle);
end

fclose(file_handle);
plot(data(:,1), data(:,2));