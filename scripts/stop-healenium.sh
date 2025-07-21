#!/bin/bash

# Healenium Backend Services Stopper Script
echo "🛑 Stopping Healenium Backend Services..."

# Check if docker-compose.yml exists
if [ ! -f "docker-compose.yml" ]; then
    echo "❌ Error: docker-compose.yml not found. Run this script from project root."
    exit 1
fi

echo "🔽 Stopping all Healenium services..."
docker-compose down

echo "🧹 Cleaning up containers..."
docker container prune -f

echo "📊 Current Docker status:"
docker ps --filter "name=healenium" --format "table {{.Names}}\t{{.Status}}\t{{.Ports}}"

echo ""
echo "✅ Healenium services stopped successfully!"
echo ""
echo "💡 Note: Data is preserved in Docker volumes."
echo "   To completely remove data: docker-compose down -v" 