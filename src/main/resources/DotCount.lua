local key = KEYS[1]
if (redis.call('get',key) == false) then
    redis.call('set',key,1)
    return string.format("%d-0001",id)
else
    redis.call('set',key,id + 1)
    return string.format("%d-0001",id)
end
