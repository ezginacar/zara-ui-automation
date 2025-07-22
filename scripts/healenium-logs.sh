#!/bin/bash

# Healenium Logs Viewer Script
echo "📋 Healenium Services Logs Viewer"
echo ""

# Check if services are running
if ! docker ps | grep -q "healenium"; then
    echo "❌ No Healenium services are currently running."
    echo "   Start services first: ./scripts/start-healenium.sh"
    exit 1
fi

# Function to show service logs
show_logs() {
    local service=$1
    local container=$2
    
    echo "=== $service Logs ==="
    if docker ps | grep -q "$container"; then
        docker logs --tail=20 "$container"
    else
        echo "❌ $service is not running"
    fi
    echo ""
}

# Show logs for all services
show_logs "PostgreSQL Database" "healenium-postgres"
show_logs "Healenium Backend" "healenium-backend"
show_logs "Healenium Proxy" "healenium-proxy"
show_logs "Selector Inspector" "healenium-selector-inspector"

echo "💡 To follow live logs for a specific service:"
echo "   docker logs -f healenium-backend"
echo "   docker logs -f healenium-proxy"
echo ""
echo "📊 All services status:"
docker ps --filter "name=healenium" --format "table {{.Names}}\t{{.Status}}\t{{.Ports}}" 