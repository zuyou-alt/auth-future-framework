-- 定义变量
local key = KEYS[1]  -- 获取第一个参数作为key值
local time = tonumber(ARGV[1])  -- 获取第一个参数并转为数字类型
local count = tonumber(ARGV[2])  -- 获取第二个参数并转为数字类型
-- 获取当前计数器的值
local current = redis.call('get',key)
-- 若current不为空 且 current值大于最大值，返回当前current值
if current and tonumber(current)>count then
    return tonumber(current)
end
-- 若current值小于最大值，自增+1，若当前的key为null，那么执行redis.call('incr', key)会将key的值初始化为0，并将current设置为1并存入redis的value
current = redis.call('incr',key)
--current==1，表示首次进行初始化，在redis中存入限流key
if(tonumber(current)==1) then
    redis.call('expire',key,time)
end
return tonumber(current)




