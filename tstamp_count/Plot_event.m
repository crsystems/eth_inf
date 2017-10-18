prompt = 'Please enter the filename of the logfile to be parsed: ';
data = [zeros zeros];
count = 0;
filename = input(prompt);
file_handle = fopen(filename, 'r');


timestamp = datetime(str2num(fgetl(file_handle)), 'ConvertFrom', 'posixtime');
time = datestr(timestamp);

temp = fgetl(file_handle);
while temp ~= -1
    count = count + 1;
    data = [data; str2num(temp) count];
    temp = fgetl(file_handle);
end
fclose(file_handle);

avg = (count/data(end,1)) * 60;
disp(avg);

figure
plot(data(:,1), data(:,2));
title(time);
