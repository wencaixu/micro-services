if (redis.call("exists", KEYS[1]) == 1)
then
    local stockCount = redis.call("incrby", KEYS[1], -1)
    if (stockCount < 0)
    then
        return 0;
    end
    return tonumber(stockCount)
else
    return 0;
end