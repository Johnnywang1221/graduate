x = 0:0.01:5;
y = normcdf(x,2.5,0.75);
plot(x,y)
xlabel('用户感知开销')
ylabel('累积概率密度')